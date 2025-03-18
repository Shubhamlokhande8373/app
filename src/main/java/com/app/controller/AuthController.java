package com.app.controller;

import com.app.entity.User;
import com.app.payload.JWTTokenDto;
import com.app.payload.LoginDto;
import com.app.repository.UserRepository;
import com.app.service.JWTService;
import com.app.service.OTPService;
import com.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private UserRepository userRepository;
    private UserService userService;
    private OTPService otpService;
    private JWTService jwtService;


    // private PasswordEncoder passwordEncoder;  // for encripting password 1st

    public AuthController(UserRepository userRepository, UserService userService, OTPService otpService, JWTService jwtService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.otpService = otpService;
        this.jwtService = jwtService;
    }

    // http://localhost:8080/api/v1/auth/signup
    @PostMapping("/signup")
    public ResponseEntity<?> createUser(
            @RequestBody User user
    ) {
        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent()) {
            return new ResponseEntity<>("Username already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User> opEmail = userRepository.findByEmailId(user.getEmailId());
        if (opEmail.isPresent()) {
            return new ResponseEntity<>("EmailId already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }

//        String encodedPassword = passwordEncoder.encode(user.getPassword());  //1st aparoch encriptpassword
//        user.setPassword(encodedPassword);

        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)); //2nd aparoch encriptpassword
        user.setPassword(hashpw);
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return new ResponseEntity<>(" user created", HttpStatus.CREATED);
    }

    // http://localhost:8080/api/v1/auth/content-manager-signup

    @PostMapping("/content-manager-signup")
    public ResponseEntity<?> createContentManagerUser(
            @RequestBody User user
    ) {
        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent()) {
            return new ResponseEntity<>("Username already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User> opEmail = userRepository.findByEmailId(user.getEmailId());
        if (opEmail.isPresent()) {
            return new ResponseEntity<>("EmailId already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }

//        String encodedPassword = passwordEncoder.encode(user.getPassword());  //1st aparoch encriptpassword
//        user.setPassword(encodedPassword);

        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)); //2nd aparoch encriptpassword
        user.setPassword(hashpw);
        user.setRole("ROLE_CONTENTMANAGER");
        userRepository.save(user);
        return new ResponseEntity<>(" user created", HttpStatus.CREATED);
    }

    // http://localhost:8080/api/v1/auth/blog-manager-signup
    @PostMapping("/blog-manager-signup")
    public ResponseEntity<?> createBlogManagerUser(
            @RequestBody User user
    ) {
        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent()) {
            return new ResponseEntity<>("Username already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User> opEmail = userRepository.findByEmailId(user.getEmailId());
        if (opEmail.isPresent()) {
            return new ResponseEntity<>("EmailId already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }

//        String encodedPassword = passwordEncoder.encode(user.getPassword());  //1st aparoch encriptpassword
//        user.setPassword(encodedPassword);

        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)); //2nd aparoch encriptpassword
        user.setPassword(hashpw);
        user.setRole("ROLE_BLOGMANAGER");
        userRepository.save(user);
        return new ResponseEntity<>(" user created", HttpStatus.CREATED);
    }
    //http://localhost:8080/api/v1/auth/getmessage
//    @GetMapping("/getmessage")
//    public String getMessage(){
//        return"hello";
//    }


//    @PostMapping("/usersign")
//    public String userSignIn(
//            @RequestBody LoginDto dto
//
//    ){
//        boolean status = userService.verifyLogin(dto);
//        if(status){
//            return "correct";
//        }
//        return "Invalid username/ password";
//    }

    // http://localhost:8080/api/v1/auth/usersign
    @PostMapping("/usersign")
    public ResponseEntity<?> userSignIn(
            @RequestBody LoginDto dto

    ) {
        String jwtToken = userService.verifyLogin(dto);
        if (jwtToken != null) {
            JWTTokenDto tokenDto = new JWTTokenDto();
            tokenDto.setToken(jwtToken);
            tokenDto.setTokenType("JWT");
            return new ResponseEntity<>(tokenDto, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Invalid token", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //http://localhost:8080/api/v1/auth/login-otp?mobile=9395318161
    @PostMapping("/login-otp")
    public String generateOTP(
            @RequestParam String mobile) {

        Optional<User> opUser = userRepository.findByMobile(mobile);
        if (opUser.isPresent()) {
            String otp = otpService.generateOTP(mobile);
            return otp + " " + mobile;
        }
        return "User not found";
    }
    //http://localhost:8080/api/v1/auth/validate-otp?mobile=9395318161&otp=550411
    @PostMapping("/validate-otp")
    public String validateOTP(
            @RequestParam String mobile,
            @RequestParam String otp){
        boolean status = otpService.validateOTP(mobile, otp);
        if(status){
            Optional<User> opUser = userRepository.findByMobile(mobile);
            if(opUser.isPresent()){
                String jwtToken  = jwtService.generateToken(opUser.get().getUsername());
                return jwtToken;
            }
            
        }
        return status ? "otp valid successfully" : "Invalid OTP";
    }
}
