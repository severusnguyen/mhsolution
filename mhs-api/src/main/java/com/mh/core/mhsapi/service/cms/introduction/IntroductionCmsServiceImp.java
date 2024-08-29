package com.mh.core.mhsapi.service.cms.introduction;

import com.mh.core.mhsapi.service.cms.AbsCmsService;
import com.mh.core.mhscommons.data.mappers.introduction.IntroductionMapper;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.introduction.IntroductionRequest;
import com.mh.core.mhscommons.data.response.introduction.IntroductionResponse;
import com.mh.core.mhscommons.data.tables.pojos.Introduction;
import com.mh.core.mhsconfig.config.exception.ApiException;
import com.mh.core.mhsrepository.repository.introduction.IntroductionRepositoryImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mh.core.mhscommons.data.constant.message.introduction.IntroductionMessageConstant.INTRODUCTION_NOT_FOUND;
import static com.mh.core.mhscommons.data.constant.message.introduction.IntroductionMessageConstant.INTRODUCTION_SYSTEM_ERROR;

@Service
@Slf4j
public class IntroductionCmsServiceImp extends AbsCmsService<IntroductionRequest, IntroductionResponse, Introduction, Integer, IntroductionRepositoryImp, IntroductionMapper>
                                    implements IIntroductionCmsService {


    @Override
    protected String getPermissionCode() {
        return "introduction";
    }

    public IntroductionCmsServiceImp(IntroductionRepositoryImp repositoryImp, IntroductionMapper mapper) {
        this.repository = repositoryImp;
        this.mapper = mapper;
    }


}
