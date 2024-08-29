package com.mh.core.mhsapi.service.api.cooperation_contact;

import com.mh.core.mhsapi.service.api.AbsService;
import com.mh.core.mhscommons.data.mappers.cooperation_contact.CooperationContactMapper;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.cooperation_contact.CooperationContactRequest;
import com.mh.core.mhscommons.data.response.cooperation_contact.CooperationContactResponse;
import com.mh.core.mhscommons.data.tables.pojos.CooperationContact;
import com.mh.core.mhsconfig.config.exception.ApiException;
import com.mh.core.mhsrepository.repository.cooperation_contact.CooperationContactRepositoryImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.mh.core.mhscommons.data.constant.message.coopearion_contact.CooperationContactMessageConstant.*;
import static com.mh.core.mhscommons.data.constant.message.MessageResponse.SERVER_ERROR;


@Service
@Slf4j
public class CooperationContactServiceImp extends AbsService<CooperationContactRequest, CooperationContactResponse, CooperationContact, Integer, CooperationContactRepositoryImp, CooperationContactMapper>
        implements ICooperationContactService {

    @Autowired
    public CooperationContactServiceImp(CooperationContactRepositoryImp repository, CooperationContactMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CooperationContactResponse findById(Integer integer) {
        try {
            CooperationContact cooperationContact = repository.findById(integer)
                    .orElseThrow(() -> new ApiException(COOPERATION_NOT_FOUND, 404));
            CooperationContactResponse cooperationContactResponse = mapper.toResponse(cooperationContact);
            return cooperationContactResponse;
        } catch (ApiException apiException) {
            log.error("[ERROR] findById {} " + apiException.getMessage());
            throw new ApiException(apiException.getMessage(), apiException.getCode());
        }
    }

    @Override
    public Page<CooperationContactResponse> search(SearchRequest searchRequest) {
        try {
            List<CooperationContact> cooperationContacts = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            List<CooperationContactResponse> cooperationContactResponses = mapper.toResponses(cooperationContacts);
            return new Page<CooperationContactResponse>()
                    .setTotal(total)
                    .setItems(cooperationContactResponses);
        } catch (Exception exception) {
            log.error("[ERROR] search {} " + exception.getMessage());
            return new Page<CooperationContactResponse>()
                    .setTotal(0L)
                    .setItems(new ArrayList<>());
        }
    }


    @Override
    public Page<CooperationContactResponse> select(SearchRequest searchRequest) {
        try {
            List<CooperationContact> cooperationContacts = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            List<CooperationContactResponse> cooperationContactResponses = new ArrayList<>();
            if (total != 0) {
                cooperationContactResponses = mapper.toResponses(cooperationContacts);
            }
            return new Page<>(total, searchRequest, cooperationContactResponses);
        } catch (Exception exception) {
            log.error("[ERROR] select {} " + exception.getMessage());
            return new Page<CooperationContactResponse>()
                    .setTotal(0L)
                    .setItems(new ArrayList<>());
        }
    }
}
