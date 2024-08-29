package com.mh.core.mhscommons.data.model.user;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class UserAuthorDTO {
    private Integer userId;
    private List<String> roles;
}