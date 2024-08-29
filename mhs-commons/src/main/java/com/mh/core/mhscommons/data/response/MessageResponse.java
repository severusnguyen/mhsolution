package com.mh.core.mhscommons.data.response;

import lombok.Data;

@Data
public class MessageResponse extends BaseResponse {
    private String content;

    public MessageResponse(String content) {
        this.content = content;
    }
}
