package com.mh.core.mhsapi.controller.api.partner;

import com.mh.core.mhsapi.service.api.partner.IPartnerService;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.response.partner.PartnerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public/partner")
public class PartnerController {

    private IPartnerService service;

    @Autowired
    public PartnerController(IPartnerService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DfResponse<PartnerResponse>> detail(
            @PathVariable("id") int id
    ){
        return DfResponse
                .okEntity(service.findById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<PartnerResponse>>> search(
            @RequestBody SearchRequest searchRequest
    ){
        return DfResponse
                .okEntity(service.search(searchRequest));
    }

    @PostMapping("/select")
    public ResponseEntity<DfResponse<Page<PartnerResponse>>> select(
            @RequestBody SearchRequest searchRequest
    ){
        return DfResponse
                .okEntity(service.search(searchRequest));
    }
}
