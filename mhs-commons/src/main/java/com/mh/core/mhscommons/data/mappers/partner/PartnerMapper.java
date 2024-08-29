package com.mh.core.mhscommons.data.mappers.partner;

import com.mh.core.mhscommons.data.mappers.BaseMap;
import com.mh.core.mhscommons.data.request.partner.PartnerRequest;
import com.mh.core.mhscommons.data.response.partner.PartnerResponse;
import com.mh.core.mhscommons.data.tables.pojos.Partner;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static com.mh.core.mhscommons.utils.SeoUtils.*;

@Mapper(componentModel = "spring")
public abstract class PartnerMapper extends BaseMap<PartnerRequest, PartnerResponse, Partner> {
    @AfterMapping
    protected void afterResponse(Partner po, @MappingTarget PartnerResponse rp) {
        String name = rp.getName();
        String id = String.valueOf(rp.getId());
        rp.setSeoId(generateSeoId(name, id));
    }
}
