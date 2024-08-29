package com.mh.core.mhsapi.service.cms.auth;

import com.mh.core.mhscommons.data.response.TokenResponse;
import com.mh.core.mhscommons.data.response.auth.LoginRequest;
import com.mh.core.mhscommons.data.response.user.UserRequest;
import com.mh.core.mhscommons.data.response.user.UserResponse;

public interface IAuthService {
    TokenResponse authenticate(LoginRequest loginRequest);
    UserResponse register(UserRequest userRequest);
    TokenResponse refreshToken(String token);
}
