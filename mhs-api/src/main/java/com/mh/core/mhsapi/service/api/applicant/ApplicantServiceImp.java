package com.mh.core.mhsapi.service.api.applicant;

import com.mh.core.mhsapi.service.api.AbsService;
import com.mh.core.mhsapi.service.api.job_recruitment.IJobRecruitmentService;
import com.mh.core.mhscommons.data.mappers.applicant.ApplicantMapper;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.applicant.ApplicantRequest;
import com.mh.core.mhscommons.data.response.applicant.ApplicantResponse;
import com.mh.core.mhscommons.data.response.job_recruitment.JobRecruitmentResponse;
import com.mh.core.mhscommons.data.tables.pojos.Applicant;
import com.mh.core.mhsconfig.config.exception.ApiException;
import com.mh.core.mhsrepository.repository.applicant.ApplicantRepositoryImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

import static com.mh.core.mhsapi.service.api.utils.YandexEmailUtil.*;
import static com.mh.core.mhscommons.data.constant.message.MessageResponse.THANKS_APPLICANT;
import static com.mh.core.mhscommons.utils.CollectionUtils.getOrDefault;
import static com.mh.core.mhscommons.data.constant.message.MessageResponse.HAVE_APPLICANT;
import static com.mh.core.mhscommons.data.constant.message.applicant.ApplicantMessageConstant.*;

@Service
@Slf4j
public class ApplicantServiceImp extends AbsService<ApplicantRequest, ApplicantResponse, Applicant, Integer, ApplicantRepositoryImp, ApplicantMapper>
        implements IApplicantService {

    private IJobRecruitmentService jobRecruitmentService;

    @Autowired
    public ApplicantServiceImp(ApplicantRepositoryImp repository, ApplicantMapper mapper, IJobRecruitmentService jobRecruitmentService) {
        this.repository = repository;
        this.mapper = mapper;
        this.jobRecruitmentService = jobRecruitmentService;
    }

    @Override
    public ApplicantResponse findById(Integer integer) {
        try {
            Applicant applicant = repository.findById(integer)
                    .orElseThrow(() -> new ApiException(APPLICANT_NOT_FOUND, 404));
            ApplicantResponse applicantResponse = mapper.toResponse(applicant);
            return applicantResponse;
        } catch (ApiException apiException) {
            log.error("[ERROR] findById {} " + apiException.getMessage());
            throw new ApiException(apiException.getMessage(), apiException.getCode());
        }
    }

    @Override
    public Page<ApplicantResponse> search(SearchRequest searchRequest) {
        try {
            List<Applicant> applicants = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            List<ApplicantResponse> applicantResponses = mapper.toResponses(applicants);
            return new Page<ApplicantResponse>()
                    .setTotal(total)
                    .setItems(applicantResponses);
        } catch (Exception exception) {
            log.error("[ERROR] search {} " + exception.getMessage());
            return new Page<ApplicantResponse>()
                    .setTotal(0L)
                    .setItems(new ArrayList<>());
        }
    }


    @Override
    public Page<ApplicantResponse> select(SearchRequest searchRequest) {
        try {
            List<Applicant> applicants = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            List<ApplicantResponse> applicantResponses = mapper.toResponses(applicants);
            return new Page<>(total, searchRequest, applicantResponses);
        } catch (Exception exception) {
            log.error("[ERROR] search {} " + exception.getMessage());
            return new Page<>(0L, searchRequest, new ArrayList<>());
        }
    }

    @Override
    public ApplicantResponse insert(ApplicantRequest request) {
        Integer jobId = getOrDefault(request.getJobId(), 1);
        JobRecruitmentResponse jobRecruitmentResponse = jobRecruitmentService.findById(jobId);
        sendMailNotification(request, jobRecruitmentResponse.getTitle());
        sendEmailThanks(request, jobRecruitmentResponse.getTitle());
        return super.insert(request);
    }

    private void sendMailNotification(ApplicantRequest request, String jobTitle) {
        String content = "<p>" + "Xin chào các HR xinh đẹp của công ty MHSolution," + "</p>"
                + "<p>" + "Đã có ứng viên gửi CV với các thông tin dưới đây" + "</p>"
                + "<p><b>" + "Họ và tên:</b>" + request.getFullName() + "</p>"
                + "<p><b>" + "Số điện thoại:</b> " + request.getPhoneNumber() + "</p>"
                + "<p><b>" + "Địa chỉ mail: </b>" + request.getEmail() + "</p>"
                + "<p><b>" + "Đã ứng tuyển vào vị trí: </b>" + request.getJobId() + "</p>"
                + "<p><b>" + "Link CV: </b>" + request.getSrcFile() + "</p>";
        try {
            sendFromYandexNoreply("tranxuanbang.yt@gmail.com", HAVE_APPLICANT + jobTitle, content);
        } catch (MessagingException e) {
            log.error("[ERROR] sendMailNotification {} " + e.getMessage());
        }
    }

    private void sendEmailThanks(ApplicantRequest request, String jobTitle) {
        String content = "<p>Bạn <b>" + request.getFullName() + "</b> thân mến,</p>"
                + "<p></p>"
                + "<p>Cảm ơn bạn đã dành thời gian gửi hồ sơ tại vị trí <b>" + jobTitle + " cho chúng tôi.</b></p>"
                + "<p>Chúng tôi sẽ lưu lại hồ sơ của bạn và liên hệ ngay sau 2~3 ngày kể từ khi bạn ứng tuyển.</p>"
                + "<p>Chúc bạn có một ngày vui vẻ và tràn đầy năng lượng!</p>"
                + "<p>Trân trọng, </p>"
                + "<p></p>"
                + "<p><b>MH Solution.</b></p>";
        try {
            sendFromYandexNoreply(request.getEmail(), THANKS_APPLICANT + jobTitle, content);
        } catch (MessagingException e) {
            log.error("[ERROR] sendEmailThanks {} " + e.getMessage());
        }
    }

}
