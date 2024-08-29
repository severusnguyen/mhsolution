package com.mh.core.mhsapi.controller.cms.cooperation_contact;

import com.mh.core.mhsapi.service.cms.cooperation_contact.CooperationContactCmsServiceImp;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.cooperation_contact.CooperationContactRequest;
import com.mh.core.mhscommons.data.response.MessageResponse;
import com.mh.core.mhscommons.data.response.cooperation_contact.CooperationContactResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/cooperation")
public class CooperationContactCmsController {

    @Autowired
    private CooperationContactCmsServiceImp service;

    @GetMapping("/{id}")
    public ResponseEntity<DfResponse<CooperationContactResponse>> detail(
            @PathVariable("id") Integer id,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.findById(id, authentication));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DfResponse<MessageResponse>> delete(@PathVariable("id") Integer id, Authentication authentication) {
        return DfResponse
                .okEntity(service.deleteById(id, authentication));
    }
    @PutMapping("/{id}")
    public ResponseEntity<DfResponse<CooperationContactResponse>> update(
            @PathVariable("id") Integer id,
            @RequestBody CooperationContactRequest request,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.update(id, request, authentication));
    }

    @PostMapping("")
    public ResponseEntity<DfResponse<CooperationContactResponse>> insert(
            @RequestBody CooperationContactRequest request,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.insert(request, authentication));
    }
    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<CooperationContactResponse>>> search(
            @RequestBody SearchRequest searchRequest) {
        return DfResponse
                .okEntity(service.search(searchRequest));
    }
}
