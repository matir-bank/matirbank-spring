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
import xyz.matirbank.spring.models.entities.Photo;
import xyz.matirbank.spring.models.entities.StandardUser;
import xyz.matirbank.spring.models.requests.StandardUserSignupRequest;
import xyz.matirbank.spring.models.requests.StandardUserLoginRequest;
import xyz.matirbank.spring.models.responses.JwtResponse;
import xyz.matirbank.spring.models.responses.base.BaseResponseEntity;
import xyz.matirbank.spring.services.StandardUserService;
import xyz.matirbank.spring.security.JwtTokenUtil;
import xyz.matirbank.spring.services.PhotoService;

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
    public ResponseEntity<BaseResponseEntity<JwtResponse>> loginUser(@RequestBody StandardUserLoginRequest request) {
        StandardUser user = userService.loginUser(request);
        if (user != null) {
            String token = jwtTokenUtil.generateToken(user);
            JwtResponse jwtResponse = new JwtResponse(token);
            return new BaseResponseEntity<>().basicData(jwtResponse).getEntity();
        } else {
            return new BaseResponseEntity<>().basicError(1001, "Invalid Login Details").getEntity();
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<BaseResponseEntity<StandardUser>> createUser(@RequestBody StandardUserSignupRequest userCreateRequest) {
        if (userService.getUserByPhone(userCreateRequest.getPhone()) == null) {
            StandardUser user = userService.createUser(userCreateRequest);
            return new BaseResponseEntity<>().basicData(user).getEntity();
        } else {
            return new BaseResponseEntity<>().basicError(1002, "User Already Exists").getEntity();
        }
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/profile")
    public ResponseEntity<BaseResponseEntity<StandardUser>> getUserProfile() {
        StandardUser user = userService.getCurrentUser();
        return new BaseResponseEntity<>().basicData(user).getEntity();
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/hash/{hash}")
    public ResponseEntity<BaseResponseEntity<StandardUser>> getUserByHash(@PathVariable String hash) {
        StandardUser user = userService.getUserByHash(hash).toScopedData();
        if (user != null) {
            return new BaseResponseEntity<>().basicData(user).getEntity();
        } else {
            return new BaseResponseEntity<>().basicError(1003, "User Does Not Exists").getEntity();
        }
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/phone/{phone}")
    public ResponseEntity<BaseResponseEntity<StandardUser>> getUserByPhone(@PathVariable String phone) {
        StandardUser user = userService.getUserByPhone(phone).toScopedData();
        if (user != null) {
            return new BaseResponseEntity<>().basicData(user).getEntity();
        } else {
            return new BaseResponseEntity<>().basicError(1003, "User Does Not Exists").getEntity();
        }
    }
    
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/profile/add-photo")
    public ResponseEntity<BaseResponseEntity<StandardUser>> addProfilePhoto(@RequestParam("file") MultipartFile file) {
        StandardUser user = userService.getCurrentUser();
        
        String fileName = user.getHash() + ".jpg";
        Path filePath = Paths.get("uploads/user/photos/").toAbsolutePath().normalize();
        
        try {Files.createDirectories(filePath);}catch(Exception e){}
        
        fileName = StringUtils.cleanPath(fileName);
        String fullPath = filePath + "\\" + fileName;
        Path targetLocation = filePath.resolve(fullPath);
        
        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            Photo photos = new Photo();
            photos.setPath(targetLocation.toString());
            photos.setUrl("/uploads/user/photos/" + fileName);
            photos = photoService.savePhotoToDatabase(photos);
            // Save to Database
            user.setProfile_photo(photos);
            user = userService.updateUser(user);
        } catch(Exception e) {e.printStackTrace();}
        
        return new BaseResponseEntity<>().basicData(user).getEntity();
    }

}
