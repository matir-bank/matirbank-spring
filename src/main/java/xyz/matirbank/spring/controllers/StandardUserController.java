package xyz.matirbank.spring.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.matirbank.spring.models.entities.StandardUsers;
import xyz.matirbank.spring.models.requests.StandardUserSignupRequest;
import xyz.matirbank.spring.models.requests.StandardUserLoginRequest;
import xyz.matirbank.spring.models.responses.JwtResponse;
import xyz.matirbank.spring.models.responses.base.BaseResponseEntity;
import xyz.matirbank.spring.services.StandardUserService;
import xyz.matirbank.spring.security.JwtTokenUtil;

@RestController
@RequestMapping("/api/user")
public class StandardUserController {

    @Autowired
    StandardUserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<BaseResponseEntity<JwtResponse>> loginUser(@RequestBody StandardUserLoginRequest request) {
        StandardUsers user = userService.loginUser(request);
        if (user != null) {
            String token = jwtTokenUtil.generateToken(user);
            JwtResponse jwtResponse = new JwtResponse(token);
            return new BaseResponseEntity<>().basicData(jwtResponse).getEntity();
        } else {
            return new BaseResponseEntity<>().basicError(1001, "Invalid Login Details").getEntity();
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<BaseResponseEntity<StandardUsers>> createUser(@RequestBody StandardUserSignupRequest userCreateRequest) {
        if (userService.getUserByPhone(userCreateRequest.getPhone()) == null) {
            StandardUsers user = userService.createUser(userCreateRequest);
            return new BaseResponseEntity<>().basicData(user).getEntity();
        } else {
            return new BaseResponseEntity<>().basicError(1002, "User Already Exists").getEntity();
        }
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/profile")
    public ResponseEntity<BaseResponseEntity<StandardUsers>> getUserProfile() {
        StandardUsers user = userService.getCurrentUser();
        return new BaseResponseEntity<>().basicData(user).getEntity();
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/hash/{hash}")
    public ResponseEntity<BaseResponseEntity<StandardUsers>> getUserByHash(@PathVariable String hash) {
        StandardUsers user = userService.getUserByHash(hash).toScopedData();
        if (user != null) {
            return new BaseResponseEntity<>().basicData(user).getEntity();
        } else {
            return new BaseResponseEntity<>().basicError(1003, "User Does Not Exists").getEntity();
        }
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/phone/{phone}")
    public ResponseEntity<BaseResponseEntity<StandardUsers>> getUserByPhone(@PathVariable String phone) {
        StandardUsers user = userService.getUserByPhone(phone).toScopedData();
        if (user != null) {
            return new BaseResponseEntity<>().basicData(user).getEntity();
        } else {
            return new BaseResponseEntity<>().basicError(1003, "User Does Not Exists").getEntity();
        }
    }
    
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/profile/add-photo")
    public ResponseEntity<BaseResponseEntity<StandardUsers>> addProfilePhoto(@RequestParam("file") MultipartFile file) {
        StandardUsers user = userService.getCurrentUser();
        
        String fileName = user.getHash() + ".jpg";
        Path filePath = Paths.get("uploads/user/photos/").toAbsolutePath().normalize();
        
        try {Files.createDirectories(filePath);}catch(Exception e){}
        
        fileName = StringUtils.cleanPath(fileName);
        String fullPath = filePath + "\\" + fileName;
        Path targetLocation = filePath.resolve(fullPath);
        
        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            
            // Save to Database
            
        } catch(Exception e) {e.printStackTrace();}
        
        return new BaseResponseEntity<>().basicData(user).getEntity();
    }

}
