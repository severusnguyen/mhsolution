package com.mh.core.mhsapi.service.api.co_founder;

import com.mh.core.mhsapi.service.api.AbsService;
import com.mh.core.mhscommons.data.mappers.co_founder.CoFounderMapper;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.co_founder.CoFounderRequest;
import com.mh.core.mhscommons.data.response.co_founder.CoFounderResponse;
import com.mh.core.mhscommons.data.tables.pojos.CoFounder;
import com.mh.core.mhsconfig.config.exception.ApiException;
import com.mh.core.mhsrepository.repository.co_founder.CoFounderRepositoryImp;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static com.mh.core.mhscommons.data.constant.message.co_founder.CoFounderMessageConstant.*;
import static com.mh.core.mhscommons.data.constant.message.MessageResponse.SERVER_ERROR;


@Service
@Slf4j
public class CoFounderServiceImp extends AbsService<CoFounderRequest, CoFounderResponse, CoFounder, Integer, CoFounderRepositoryImp, CoFounderMapper>
        implements ICoFounderService {

    @Autowired
    public CoFounderServiceImp(CoFounderRepositoryImp repository, CoFounderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CoFounderResponse findById(Integer integer) {
        CoFounderResponse coFounderResponse = new CoFounderResponse();
        try {
            CoFounder coFounder = repository.findById(integer)
                    .orElseThrow(() -> new ApiException(CO_FOUNDER_NOT_FOUND, 404));
            coFounderResponse = mapper.toResponse(coFounder);
            return coFounderResponse;
        } catch (ApiException apiException) {
            log.error("[ERROR] findById {} " + apiException.getMessage());
            throw new ApiException(apiException.getMessage(), apiException.getCode());
        }
    }

    @Override
    public Page<CoFounderResponse> search(SearchRequest searchRequest) {
        try{
            List<CoFounder> coFounders = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            List<CoFounderResponse> coFounderResponses = mapper.toResponses(coFounders);
            return new Page<CoFounderResponse>()
                    .setTotal(total)
                    .setItems(coFounderResponses);
        }catch (Exception exception){
            log.error("[ERROR] search {} " + exception.getMessage());
            return new Page<CoFounderResponse>()
                    .setTotal(0L)
                    .setItems(new ArrayList<>());
        }
    }

    @Override
    public Page<CoFounderResponse> select(SearchRequest searchRequest) {
        try{
            List<CoFounder> coFounders = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            List<CoFounderResponse> coFounderResponses = mapper.toResponses(coFounders);
            return new Page<>(total, searchRequest, coFounderResponses);
        }catch (Exception exception){
            log.error("[ERROR] select {} " + exception.getMessage());
            return new Page<CoFounderResponse>()
                    .setTotal(0L)
                    .setItems(new ArrayList<>());
        }
    }
}
