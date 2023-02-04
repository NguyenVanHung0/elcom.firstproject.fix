
package com.elcom.management_library_api.controller;

import com.elcom.management_library_common.auth.CustomUserDetails;
import com.elcom.management_library_common.auth.LoginRequest;
import com.elcom.management_library_common.auth.LoginResponse;
import com.elcom.management_library_common.auth.jwt.JwtTokenProvider;
import com.elcom.management_library_common.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {
     private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    public LoginController() {
    }

    @Autowired
    private JwtTokenProvider tokenProvider;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @PostMapping("")
    public LoginResponse authenticateUser(@RequestBody LoginRequest loginRequest) {
        
        //new UserValidation().validateLogin(loginRequest.getUsername(), loginRequest.getPassword());
        
        // Xác thực thông tin người dùng Request lên, nếu không xảy ra exception tức là thông tin hợp lệ
        Authentication authentication = null;
        try {
             Authentication authentications =  new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
                );
             authentication = authenticationManager.authenticate(authentications);
        }catch(AuthenticationException ex) {
            LOGGER.error(ex.toString());
            throw new ValidationException("Sai username/password.");
        }
        
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }
}
