package com.mh.core.mhsapi.service.api.introduction;

import com.mh.core.mhsapi.service.api.AbsService;

import com.mh.core.mhscommons.data.mappers.introduction.IntroductionMapper;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.introduction.IntroductionRequest;
import com.mh.core.mhscommons.data.response.introduction.IntroductionResponse;
import com.mh.core.mhscommons.data.tables.pojos.Introduction;
import com.mh.core.mhsconfig.config.exception.ApiException;
import com.mh.core.mhsrepository.repository.introduction.IntroductionRepositoryImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mh.core.mhscommons.data.Tables.INTRODUCTION;
import static com.mh.core.mhscommons.data.constant.message.introduction.IntroductionMessageConstant.*;
import static com.mh.core.mhscommons.data.constant.message.MessageResponse.SERVER_ERROR;

@Service
@Slf4j
public class IntroductionServiceImp extends AbsService<IntroductionRequest, IntroductionResponse, Introduction, Integer, IntroductionRepositoryImp, IntroductionMapper>
        implements IIntroductionService{

    @Autowired
    public IntroductionServiceImp(IntroductionRepositoryImp introductionRepositoryImp, IntroductionMapper introductionMapper) {
        this.repository = introductionRepositoryImp;
        this.mapper = introductionMapper;
    }

    @Override
    public IntroductionResponse findById(Integer integer) {
        try{

            Introduction introduction = repository.findById(integer)
                    .orElseThrow(() -> new ApiException(INTRODUCTION_NOT_FOUND, 404));

            IntroductionResponse introductionResponse = mapper.toResponse(introduction);

            return introductionResponse;
        }catch (ApiException apiException){
            log.error("[ERROR] findById {} " + apiException.getMessage());
            throw new ApiException(apiException.getMessage(), apiException.getCode());
        }
    }

    @Override
    public Page<IntroductionResponse> search(SearchRequest searchRequest) {
        try{
            List<Introduction> introductions = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            List<IntroductionResponse> introductionResponses =  mapper.toResponses(introductions);
            return new Page<IntroductionResponse>()
                    .setTotal(total)
                    .setItems(introductionResponses);
        }catch (Exception exception){
            log.error("[ERROR] search{} " + exception.getMessage());
            return new Page<IntroductionResponse>()
                    .setTotal(0L)
                    .setItems(new ArrayList<>());
        }
    }


    @Override
    public Page<IntroductionResponse> select(SearchRequest searchRequest) {
        try {
            List<Introduction> introductions = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            List<IntroductionResponse> introductionResponses = mapper.toResponses(introductions);
            return new Page<>(total, searchRequest, introductionResponses);
        }catch (Exception exception){
            log.error("[ERROR] select {} " + exception.getMessage());
            return new Page<>(0L, searchRequest, new ArrayList<>());
        }
    }
}
