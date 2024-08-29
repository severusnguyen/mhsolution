package com.mh.core.mhscommons.data.response.user_info_response;

import com.mh.core.mhscommons.data.tables.pojos.User;
import lombok.Data;

@Data
public class UserInfoResponse {
    private User user;

    private String token;
}