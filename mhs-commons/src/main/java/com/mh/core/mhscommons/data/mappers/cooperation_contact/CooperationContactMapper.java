package com.mh.core.mhscommons.data.mappers.cooperation_contact;

import com.mh.core.mhscommons.data.mappers.BaseMap;
import com.mh.core.mhscommons.data.request.cooperation_contact.CooperationContactRequest;
import com.mh.core.mhscommons.data.response.cooperation_contact.CooperationContactResponse;
import com.mh.core.mhscommons.data.tables.pojos.CooperationContact;
import static com.mh.core.mhscommons.utils.SeoUtils.*;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class CooperationContactMapper extends BaseMap<CooperationContactRequest, CooperationContactResponse, CooperationContact> {
    @AfterMapping
    protected void afterResponse(CooperationContact po, @MappingTarget CooperationContactResponse rp) {
        final String fullName = rp.getFullName();
        final String id = String.valueOf(rp.getId());
        rp.setSeoId(generateSeoId(fullName, id));
    }
}
