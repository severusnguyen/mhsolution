package com.mh.core.mhsapi.controller.api.email;

import com.mh.core.mhsapi.service.api.email.IEmailService;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.request.email.EmailFeedbackRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/cooperation")
public class EmailController {

    private IEmailService service;

    @Autowired
    public EmailController(IEmailService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<DfResponse<String>> feedback(
            @RequestBody EmailFeedbackRequest emailFeedbackRequest
    ) {
        return DfResponse
                .okEntity(service.sendMail(emailFeedbackRequest));
    }

}
