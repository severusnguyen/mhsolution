package com.mh.core.mhsapi.service.api.partner;

import com.mh.core.mhsapi.service.api.AbsService;
import com.mh.core.mhscommons.data.mappers.partner.PartnerMapper;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.partner.PartnerRequest;
import com.mh.core.mhscommons.data.response.partner.PartnerResponse;
import com.mh.core.mhscommons.data.tables.pojos.Partner;
import com.mh.core.mhsconfig.config.exception.ApiException;
import com.mh.core.mhsrepository.repository.partner.PartnerRepositoryImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.mh.core.mhscommons.data.constant.message.partner.PartnerMessageConstant.*;
import static com.mh.core.mhscommons.data.constant.message.MessageResponse.SERVER_ERROR;

@Service
@Slf4j
public class PartnerServiceImp extends AbsService<PartnerRequest, PartnerResponse, Partner, Integer, PartnerRepositoryImp, PartnerMapper>
        implements IPartnerService {

    @Autowired
    public PartnerServiceImp(PartnerRepositoryImp repository, PartnerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PartnerResponse findById(Integer integer) {
        try {
            Partner partner = repository.findById(integer)
                    .orElseThrow(() -> new ApiException(PARTNER_NOT_FOUND, 404));
            PartnerResponse partnerResponse = mapper.toResponse(partner);
            return partnerResponse;
        } catch (ApiException apiException) {
            log.error("[ERROR] findById {} " + apiException.getMessage());
            throw new ApiException(apiException.getMessage(), apiException.getCode());
        }
    }

    @Override
    public Page<PartnerResponse> search(SearchRequest searchRequest) {
        try {
            List<Partner> partners = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            List<PartnerResponse> partnerResponses = mapper.toResponses(partners);
            return new Page<PartnerResponse>()
                    .setTotal(total)
                    .setItems(partnerResponses);
        } catch (Exception exception) {
            log.error("[ERROR] search {} " + exception.getMessage());
            return new Page<PartnerResponse>()
                    .setTotal(0L)
                    .setItems(new ArrayList<>());
        }
    }

    @Override
    public Page<PartnerResponse> select(SearchRequest searchRequest) {
        try {
            List<Partner> partners = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            List<PartnerResponse> partnerResponses = new ArrayList<>();
            if (total != 0) {
                partnerResponses = mapper.toResponses(partners);
            }
            return new Page<>(total, searchRequest, partnerResponses);
        } catch (Exception exception) {
            log.error("[ERROR] select {} " + exception.getMessage());
            return new Page<>(0L, searchRequest, new ArrayList<>());
        }
    }
}
