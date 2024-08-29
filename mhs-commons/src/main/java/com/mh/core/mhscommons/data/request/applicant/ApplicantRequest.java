package com.mh.core.mhscommons.data.request.applicant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicantRequest {
    private String fullName;
    private String phoneNumber;
    private String email;
    private String linkProducts;
    private String srcFile;
    private Integer createBy;
    private Integer deleteBy;
    private Integer jobId;
}
