package com.mh.core.mhscommons.data.response.job_recruitment;

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
public class JobRecruitmentResponse extends BaseResponse {
    private Integer id;
    private String seoId;
    private String title;
    private String salary;
    private String level;
    private String career;
    private String jobDescription;
    private String jobRequirements;
    private String jobBenefits;
    private String workExperience;
    private String workType;
    private String workAddress;
    private String numberRecruits;
    private Long createdAt;
    private Long endTime;
    private String moreInfo;
    private String coverUrl;
    private List<Info> infos;
    private String numView;
}
