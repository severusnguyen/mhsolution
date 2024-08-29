package com.mh.core.mhsapi.controller.cms.partner;

import com.mh.core.mhsapi.service.cms.partner.PartnerCmsServiceImp;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.partner.PartnerRequest;
import com.mh.core.mhscommons.data.response.MessageResponse;
import com.mh.core.mhscommons.data.response.partner.PartnerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/partner")
public class PartnerCmsController {

    @Autowired
    private PartnerCmsServiceImp service;

    @GetMapping("/{id}")
    public ResponseEntity<DfResponse<PartnerResponse>> detail(
            @PathVariable("id") Integer id,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.findById(id, authentication));
    }

    @PostMapping("")
    public ResponseEntity<DfResponse<PartnerResponse>> insert(
            @RequestBody PartnerRequest partnerRequest,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.insert(partnerRequest, authentication));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DfResponse<PartnerResponse>> update(
            @PathVariable("id") Integer id,
            @RequestBody PartnerRequest rq,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.update(id, rq, authentication));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DfResponse<MessageResponse>> delete(
            @PathVariable("id") Integer id,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.deleteById(id, authentication));
    }


    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<PartnerResponse>>> search(
            @RequestBody SearchRequest searchRequest) {
        return DfResponse
                .okEntity(service.search(searchRequest));
    }
}
