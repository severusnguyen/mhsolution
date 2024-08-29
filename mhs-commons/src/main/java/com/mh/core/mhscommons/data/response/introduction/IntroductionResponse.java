package com.mh.core.mhscommons.data.response.introduction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mh.core.mhscommons.data.model.Competitive;
import com.mh.core.mhscommons.data.model.CoreValue;
import com.mh.core.mhscommons.data.model.Mission;
import com.mh.core.mhscommons.data.model.Vision;
import com.mh.core.mhscommons.data.response.BaseResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IntroductionResponse extends BaseResponse {
    private Integer id;
    private String seoId;
    private String title;
    private String content;
    private List<Vision> visions;
    private List<Mission> missions;
    private List<CoreValue> coreValues;
    private List<Competitive> competitives;
    private String coverUrl;
}
