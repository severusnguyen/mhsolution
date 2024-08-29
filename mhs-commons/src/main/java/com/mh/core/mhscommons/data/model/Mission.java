package com.mh.core.mhscommons.data.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Mission {
    private String title;
    private String content;
    private String src;
}
