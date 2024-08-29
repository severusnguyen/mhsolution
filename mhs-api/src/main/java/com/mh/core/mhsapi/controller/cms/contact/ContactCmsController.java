package com.mh.core.mhsapi.controller.cms.contact;

import com.mh.core.mhsapi.service.cms.contact.ContactCmsServiceImp;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.contact.ContactRequest;
import com.mh.core.mhscommons.data.response.MessageResponse;
import com.mh.core.mhscommons.data.response.contact.ContactResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/contact")
public class ContactCmsController {

    @Autowired
    private ContactCmsServiceImp service;

    @PostMapping("")
    public ResponseEntity<DfResponse<ContactResponse>> insert(
            @RequestBody ContactRequest request,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.insert(request, authentication));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DfResponse<ContactResponse>> detail(
            @PathVariable Integer id,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.findById(id, authentication));
    }


    @PutMapping("/{id}")
    public ResponseEntity<DfResponse<ContactResponse>> update(
            @PathVariable Integer id,
            @RequestBody ContactRequest request,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.update(id, request, authentication));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<DfResponse<MessageResponse>> delete(
            @PathVariable("id") Integer id,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.deleteById(id, authentication));
    }
    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<ContactResponse>>> search(
            @RequestBody SearchRequest searchRequest) {
        return DfResponse
                .okEntity(service.search(searchRequest));
    }
}
