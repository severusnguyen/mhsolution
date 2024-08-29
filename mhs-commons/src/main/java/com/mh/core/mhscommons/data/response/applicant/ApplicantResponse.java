package com.mh.core.mhscommons.data.response.applicant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mh.core.mhscommons.data.response.BaseResponse;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicantResponse extends BaseResponse {
    private Integer id;
    private Integer jobId;
    private String seoId;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String linkProducts;
    private String srcFile;
    private Long createdAt;
}
