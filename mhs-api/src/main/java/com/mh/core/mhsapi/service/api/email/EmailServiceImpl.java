package com.mh.core.mhsapi.service.api.email;

import com.mh.core.mhscommons.data.request.email.EmailFeedbackRequest;
import com.mh.core.mhsconfig.config.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

import static com.mh.core.mhsapi.service.api.utils.YandexEmailUtil.*;
import static com.mh.core.mhscommons.utils.CollectionUtils.getOrDefault;
import static com.mh.core.mhscommons.data.constant.message.MessageResponse.SUCCESS;
import static com.mh.core.mhscommons.data.constant.message.email.EmailMessageConstant.*;


@Service
@Slf4j
public class EmailServiceImpl implements IEmailService {

    @Override
    public String sendMail(EmailFeedbackRequest emailRequest) {
        try {
            String emailAddress = getOrDefault(emailRequest.getAddress(), "business@mh.media");
            String title = emailRequest.getTitle();
            String content = emailRequest.getContent();
            sendFromYandexHr(emailAddress, title, content);
        } catch (MessagingException e) {
            log.error("[ERROR] sendMail {} " + e.getMessage());
            throw new ApiException(SEND_EMAIL_FAILED, 400);
        }
        return SUCCESS;
    }
}
