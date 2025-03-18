package com.app.service;

import com.app.entity.User;

import com.app.payload.LoginDto;
import com.app.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
   private UserRepository userRepository;
   private JWTService jwtService;

    public UserService(UserRepository userRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

//    public boolean verifyLogin(
//            LoginDto dto
//    ){
//
//        Optional<User> optionalUser = userRepository.findByUsername(dto.getUsername());
//        if(optionalUser.isPresent()){
//            User user = optionalUser.get();
//           return BCrypt.checkpw(dto.getPassword(),user.getPassword());
//
//        }
//        return false;
//    }

    public String verifyLogin(
            LoginDto dto
    ){

        Optional<User> optionalUser = userRepository.findByUsername(dto.getUsername());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
           if (BCrypt.checkpw(dto.getPassword(),user.getPassword())){
            return   jwtService.generateToken(user.getUsername());
            }

        }
        return null;
    }
}
