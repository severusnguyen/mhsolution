package com.mh.core.mhsapi.controller.cms.auth;

import com.mh.core.mhsapi.service.cms.auth.AuthServiceImp;
import com.mh.core.mhsapi.service.cms.auth.IAuthService;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.response.TokenResponse;
import com.mh.core.mhscommons.data.response.auth.LoginRequest;
import com.mh.core.mhscommons.data.response.user.UserRequest;
import com.mh.core.mhscommons.data.response.user.UserResponse;
import com.mh.core.mhscommons.data.response.user_info_response.UserInfoResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class AuthController {

    @Autowired
    AuthServiceImp service;

//    IAuthService service;
//
//    @Autowired
//    public AuthController(IAuthService service) {
//        this.service = service;
//    }

    @PostMapping("/user/authenticate")
    public ResponseEntity<DfResponse<TokenResponse>> login(@RequestBody LoginRequest loginRequest){
        return DfResponse
                .okEntity(service.authenticate(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<DfResponse<UserResponse>> register(@RequestBody UserRequest request){
        return DfResponse
                .okEntity(service.register(request));
    }

    @GetMapping("/user/me")
    public ResponseEntity<DfResponse<UserInfoResponse>> getMe(Principal principal) {
        UserInfoResponse userInfoResponse = service.getMe();

        return DfResponse
                .okEntity(userInfoResponse);
    }


    @GetMapping("/cms/auth/logout")
    public ResponseEntity<DfResponse<TokenResponse>> logout(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.split(" ")[1].trim();
        TokenResponse tokenResponse = service.refreshToken(token);

        return DfResponse
                .okEntity(tokenResponse);
    }

}
