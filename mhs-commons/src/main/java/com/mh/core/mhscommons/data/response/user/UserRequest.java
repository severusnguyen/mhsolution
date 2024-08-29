package com.mh.core.mhscommons.data.response.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRequest {
    private Integer id;
    private String name;
    private String alias;
    private String avatar;
    private String phone;
    private String email;
    private String empCode;
    private String password;
    private LocalDateTime regisDate;
    private Short gender;
    private String facebookLink;
    private String role;
    private String note;
}
