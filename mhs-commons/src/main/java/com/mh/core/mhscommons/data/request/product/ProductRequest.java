package com.mh.core.mhscommons.data.request.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mh.core.mhscommons.data.model.Info;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductRequest {
    private String title;
    private List<Info> contents;
    private String shortContent;
    private String website;
    private String landingPage;
    private String phoneNumber;
    private List<Info> infos;
    private String infoUrl;
    private String coverUrl;
    private String iconUrl;
    private String iconHoverUrl;
    private Integer createBy;
    private Integer deleteBy;
}
