package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;
@Configuration
public class SecurityConfig {
   private JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean // object created        //Beanlifecycle create initmethod destroymethod
public SecurityFilterChain securityFilterChain(
      // h(cd)2
      HttpSecurity http
) throws Exception {
    http.csrf().disable().cors().disable();

    //haap
    http.authorizeHttpRequests().anyRequest().permitAll();
//        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);
//        http.authorizeHttpRequests()
//                .requestMatchers("/api/v1/auth/signup", "/api/v1/auth/usersign","/api/v1/auth/content-manager-signup","/api/v1/auth/blog-manager-signup","/api/v1/auth/login-otp")
//                .permitAll()
//                .requestMatchers("/api/v1/car/add-car").hasRole("CONTENTMANAGER")
//                .anyRequest().authenticated();
    return http.build();
}

//@Bean
//public PasswordEncoder getPasswordEncoder(){    //1st aparocha encriptpassword
//        return new BCryptPasswordEncoder();
//}
}

