package com.mh.core.mhsapi.controller.api.contact;

import com.mh.core.mhsapi.service.api.contact.IContactService;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.response.contact.ContactResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public/contact")
public class ContactController {

    private IContactService service;

    @Autowired
    public ContactController(IContactService service) {
        this.service = service;
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<DfResponse<ContactResponse>> detail(
            @PathVariable("id") Integer id
    ) {
        return DfResponse
                .okEntity(service.findById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<ContactResponse>>> search(
            @RequestBody SearchRequest searchRequest
    ) {
        return DfResponse
                .okEntity(service.search(searchRequest));
    }

    @PostMapping("/select")
    public ResponseEntity<DfResponse<Page<ContactResponse>>> select(
            @RequestBody SearchRequest searchRequest
    ) {
        return DfResponse
                .okEntity(service.select(searchRequest));
    }

}
