package com.mh.core.mhscommons.data.mappers.applicant;

import com.mh.core.mhscommons.data.mappers.BaseMap;
import com.mh.core.mhscommons.data.request.applicant.ApplicantRequest;
import com.mh.core.mhscommons.data.response.applicant.ApplicantResponse;
import com.mh.core.mhscommons.data.tables.pojos.Applicant;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static com.mh.core.mhscommons.utils.SeoUtils.*;

@Mapper(componentModel = "spring")
public abstract class ApplicantMapper extends BaseMap<ApplicantRequest, ApplicantResponse, Applicant> {
    @AfterMapping
    protected void afterResponse(Applicant po, @MappingTarget ApplicantResponse rp) {
        final String seoId = rp.getSeoId();
        final String id = String.valueOf(rp.getId());
        rp.setSeoId(generateSeoId(rp.getSeoId(), String.valueOf(rp.getId())));
    }
}
