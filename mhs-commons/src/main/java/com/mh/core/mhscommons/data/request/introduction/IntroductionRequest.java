package com.mh.core.mhscommons.data.request.introduction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mh.core.mhscommons.data.model.Competitive;
import com.mh.core.mhscommons.data.model.CoreValue;
import com.mh.core.mhscommons.data.model.Mission;
import com.mh.core.mhscommons.data.model.Vision;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IntroductionRequest {
    private String title;
    private String content;
    private List<Vision> visions;
    private List<Mission> missions;
    private List<CoreValue> coreValues;
    private List<Competitive> competitives;
    private Integer createBy;
    private Integer deleteBy;
    private String coverUrl;
}
