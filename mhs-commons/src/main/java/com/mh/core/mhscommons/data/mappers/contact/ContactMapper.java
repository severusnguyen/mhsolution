package com.mh.core.mhscommons.data.mappers.contact;

import com.mh.core.mhscommons.core.json.JsonArray;
import com.mh.core.mhscommons.data.mappers.BaseMap;
import com.mh.core.mhscommons.data.model.Social;
import com.mh.core.mhscommons.data.request.contact.ContactRequest;
import com.mh.core.mhscommons.data.response.contact.ContactResponse;
import com.mh.core.mhscommons.data.tables.pojos.Contact;
import lombok.SneakyThrows;
import org.jooq.JSON;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

import static com.mh.core.mhscommons.utils.SeoUtils.generateSeoId;
import static com.mh.core.mhscommons.config.JsonMapper.getObjectMapper;


@Mapper(componentModel = "spring")
public abstract class ContactMapper extends BaseMap<ContactRequest, ContactResponse, Contact> {
    @Named("toPOJO")
    @Mapping(target = "social", ignore = true)
    public abstract Contact toPOJO(ContactRequest request);

    @Named("toResponse")
    @Mapping(target = "socials", ignore = true)
    public abstract ContactResponse toResponse(Contact pojo);

    public abstract List<ContactResponse> toResponses(List<Contact> pojos);

    @SneakyThrows
    @AfterMapping
    protected void afterToPojo(@MappingTarget Contact contact, ContactRequest request) {
        if (request.getSocials() != null) {
            final String info = getObjectMapper().writeValueAsString(request.getSocials());
            contact.setSocial(JSON.valueOf(info));
        }
    }

    @SneakyThrows
    @AfterMapping
    protected void afterToResponse(@MappingTarget ContactResponse response, Contact contact) {
        if (contact.getSocial() != null) {
            List<Social> socialResponses = new JsonArray(contact.getSocial().data()).mapTo(Social.class);
            response.setSocials(socialResponses);
        } else {
            response.setSocials(new ArrayList<>());
        }
    }

    @AfterMapping
    protected void afterResponse(Contact contact, @MappingTarget ContactResponse response) {
        response.setSeoId(generateSeoId(response.getAddress(), String.valueOf(response.getId())));
    }
}
