package com.mh.core.mhscommons.data.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Seo {
    private String keywords;
    private String description;
}
