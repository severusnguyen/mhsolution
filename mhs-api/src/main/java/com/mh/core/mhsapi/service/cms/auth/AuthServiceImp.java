package com.mh.core.mhsapi.service.cms.auth;

import com.mh.core.mhscommons.data.model.user.UserAuthorDTO;
import com.mh.core.mhscommons.data.response.TokenResponse;
import com.mh.core.mhscommons.data.response.auth.LoginRequest;
import com.mh.core.mhscommons.data.response.user.UserRequest;
import com.mh.core.mhscommons.data.response.user.UserResponse;
import com.mh.core.mhscommons.data.response.user_info_response.UserInfoResponse;
import com.mh.core.mhscommons.data.tables.pojos.Role;
import com.mh.core.mhscommons.data.tables.pojos.User;
import com.mh.core.mhsconfig.config.auth_config.JwtUtil;
import com.mh.core.mhsconfig.config.exception.ApiException;
import com.mh.core.mhsrepository.repository.user.IUserRepository;
import com.mh.core.mhsrepository.repository.user.UserRepositoryImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.mh.core.mhscommons.data.constant.message.MessageResponse.USERNAME_NOT_FOUND;
import static com.mh.core.mhscommons.data.constant.message.user.UserMessageConstant.PASSWORD_INVALID;

@Service
@Slf4j
public class AuthServiceImp implements IAuthService {

    private PasswordEncoder passwordEncoder;

    private IUserRepository userRepository;

    private JwtUtil jwtUtil;

    @Autowired
    UserRepositoryImp userRepositoryImp;

    @Autowired
    public AuthServiceImp(PasswordEncoder passwordEncoder, IUserRepository userRepository, JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public TokenResponse authenticate(LoginRequest loginRequest) {
        try {
            User user = userRepository.findUserByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new ApiException(USERNAME_NOT_FOUND, 401));
            String requestPassword = loginRequest.getPassword();
            String truePassword = user.getPassword();
            if (!passwordEncoder.matches(requestPassword, truePassword)){
                throw new ApiException(PASSWORD_INVALID, 401);
            }else{
                List<String> roles = userRepository.findRoleNameByUserId(user.getId());
                String token = jwtUtil.generateToken(user, roles);
                return new TokenResponse(token);
            }
        } catch (ApiException exception) {
            log.error("[ERROR] authenticate {} " + exception.getMessage());
            throw new ApiException(exception.getMessage(), exception.getCode());
        }
    }

    @Override
    public UserResponse register(UserRequest userRequest) {
        return null;
    }

    @Override
    public TokenResponse refreshToken(String token) {
        String newToken = jwtUtil.refreshToken(token);

        return new TokenResponse(newToken);
    }

    public UserInfoResponse getMe() {
        UserAuthorDTO userAuthorDTO = (UserAuthorDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> userOptional = userRepositoryImp.findById(userAuthorDTO.getUserId());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            user.setPassword("");

            return toUserUserInfoResponse(user);
        } else {
            throw new UsernameNotFoundException("User not found with ID: " + userAuthorDTO.getUserId());
        }
    }

    private UserInfoResponse toUserUserInfoResponse(User user) {
        UserInfoResponse response = new UserInfoResponse();

        response.setUser(user);

        return response;
    }
}
