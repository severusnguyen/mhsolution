package com.mh.core.mhscommons.data.response.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mh.core.mhscommons.data.model.Info;
import com.mh.core.mhscommons.data.response.BaseResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse extends BaseResponse {
    private Integer id;
    private String seoId;
    private String title;
    private List<Info> contents;
    private String shortContent;
    private String website;
    private String landingPage;
    private List<Info> infos;
    private String infoUrl;
    private String coverUrl;
    private String iconUrl;
    private String iconHoverUrl;
    private String phoneNumber;
}
