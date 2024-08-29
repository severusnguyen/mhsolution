package com.mh.core.mhsapi.service.api.email;

import com.mh.core.mhscommons.data.request.email.EmailFeedbackRequest;

public interface IEmailService {

    String sendMail(EmailFeedbackRequest emailRequest);

}
