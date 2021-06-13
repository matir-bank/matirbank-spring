package xyz.matirbank.spring.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.matirbank.spring.models.ReturnContainer;
import xyz.matirbank.spring.models.entities.Photo;
import xyz.matirbank.spring.models.entities.StandardUser;
import xyz.matirbank.spring.models.requests.StandardUserSignupRequest;
import xyz.matirbank.spring.models.requests.StandardUserLoginRequest;
import xyz.matirbank.spring.models.responses.JwtResponse;
import xyz.matirbank.spring.models.responses.base.BaseResponse;
import xyz.matirbank.spring.models.responses.base.BaseResponseEntity;
import xyz.matirbank.spring.services.StandardUserService;
import xyz.matirbank.spring.security.JwtTokenUtil;
import xyz.matirbank.spring.services.PhotoService;
import xyz.matirbank.spring.utils.StandardErrors;

@RestController
@RequestMapping("/api/user")
public class StandardUserController {

    @Autowired
    StandardUserService userService;

    @Autowired
    PhotoService photoService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<JwtResponse>> loginUser(@RequestBody StandardUserLoginRequest request) {
        ReturnContainer<StandardUser> userContainer = userService.loginUser(request);

        if (userContainer.getStatus()) {
            String token = jwtTokenUtil.generateToken(userContainer.getData());
            JwtResponse jwtResponse = new JwtResponse(token);
            return new BaseResponseEntity<>().basicData(jwtResponse).getEntity();
        } else {
            return new BaseResponseEntity<>().basicError(StandardErrors.E1004_INCORRECT_LOGIN_DETAILS).getEntity();
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<StandardUser>> createUser(@RequestBody StandardUserSignupRequest userCreateRequest) {
        return new BaseResponseEntity<>()
                .basicData(userService.createUser(userCreateRequest))
                .getEntity();
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/profile")
    public ResponseEntity<BaseResponse<StandardUser>> getUserProfile() {
        ReturnContainer<StandardUser> userContainer = userService.getCurrentUser();
        return new BaseResponseEntity<>().basicData(userContainer).getEntity();
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/hash/{hash}")
    public ResponseEntity<BaseResponse<StandardUser>> getUserByHash(@PathVariable String hash) {
        ReturnContainer<StandardUser> userContainer = userService.getUserByHash(hash);
        return new BaseResponseEntity<>().basicData(userContainer).getEntity();
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/phone/{phone}")
    public ResponseEntity<BaseResponse<StandardUser>> getUserByPhone(@PathVariable String phone) {
        ReturnContainer<StandardUser> userContainer = userService.getUserByPhone(phone);
        return new BaseResponseEntity<>().basicData(userContainer).getEntity();
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/profile/add-photo")
    public ResponseEntity<BaseResponse<StandardUser>> addProfilePhoto(@RequestParam("file") MultipartFile file) {
        ReturnContainer<StandardUser> userContainer = userService.getCurrentUser();

        if (!userContainer.getStatus()) {
            return new BaseResponseEntity<>().basicData(userContainer).getEntity();
        }

        StandardUser user = userContainer.getData();

        String fileName = user.getHash() + ".jpg";
        Path filePath = Paths.get("uploads/user/photos/").toAbsolutePath().normalize();

        try {
            Files.createDirectories(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        fileName = StringUtils.cleanPath(fileName);
        String fullPath = filePath + "\\" + fileName;
        Path targetLocation = filePath.resolve(fullPath);

        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            Photo photos = new Photo();
            photos.setPath(targetLocation.toString());
            photos.setUrl("/uploads/user/photos/" + fileName);
            ReturnContainer<Photo> photReturnContainer = photoService.savePhotoToDatabase(photos);
            if (photReturnContainer.getStatus()) {
                // Save to Database
                user.setProfile_photo(photos);
                userContainer = userService.updateUser(user);
                return new BaseResponseEntity<>().basicData(userContainer).getEntity();
            } else {
                return new BaseResponseEntity<>().basicData(photReturnContainer).getEntity();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new BaseResponseEntity<>().basicData(user).getEntity();
    }

}
