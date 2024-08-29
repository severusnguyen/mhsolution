package com.mh.core.mhsconfig.utils;

import com.mh.core.mhscommons.data.model.user.UserAuthorDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static UserAuthorDTO extractUser(Authentication authentication) {
        UserAuthorDTO userAuthorDTO = (UserAuthorDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userAuthorDTO;
    }
}