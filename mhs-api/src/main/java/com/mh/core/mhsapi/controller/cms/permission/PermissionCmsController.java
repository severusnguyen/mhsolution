package com.mh.core.mhsapi.controller.cms.permission;

import com.mh.core.mhsapi.service.cms.permission.PermissionCmsServiceImp;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.permission.PermissionRequest;
import com.mh.core.mhscommons.data.response.MessageResponse;
import com.mh.core.mhscommons.data.response.permission.PermissionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cms/permission")
public class PermissionCmsController {

    @Autowired
    private PermissionCmsServiceImp service;

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<PermissionResponse>>> search(@RequestBody SearchRequest searchRequest) {
        return DfResponse
                .okEntity(service.search(searchRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DfResponse<PermissionResponse>> detail(
            @PathVariable("id") Integer id,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.findById(id, authentication));
    }

    @PostMapping("")
    public ResponseEntity<DfResponse<PermissionResponse>> insert(
            @RequestBody PermissionRequest request,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.insert(request, authentication));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DfResponse<PermissionResponse>> update(
            @PathVariable("id") Integer id,
            @RequestBody PermissionRequest request,
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

    @GetMapping("/all")
    public ResponseEntity<DfResponse<List<PermissionResponse>>> getAll(){
        return DfResponse
                .okEntity(service.getAll());
    }
}
