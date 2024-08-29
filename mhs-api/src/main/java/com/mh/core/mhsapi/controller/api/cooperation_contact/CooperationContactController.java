package com.mh.core.mhsapi.controller.api.cooperation_contact;

import com.mh.core.mhsapi.service.api.cooperation_contact.ICooperationContactService;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.response.cooperation_contact.CooperationContactResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public/cooperation")
public class CooperationContactController {

    private ICooperationContactService service;

    @Autowired
    public CooperationContactController(ICooperationContactService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DfResponse<CooperationContactResponse>> detail(
            @PathVariable("id") Integer id
    ){
        return DfResponse
                .okEntity(service.findById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<CooperationContactResponse>>> search(
            @RequestBody SearchRequest searchRequest
    ){
        return DfResponse
                .okEntity(service.search(searchRequest));
    }

    @PostMapping("/select")
    public ResponseEntity<DfResponse<Page<CooperationContactResponse>>> select(
            @RequestBody SearchRequest searchRequest
    ){
        return DfResponse
                .okEntity(service.select(searchRequest));
    }
}
