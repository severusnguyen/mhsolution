package com.mh.core.mhsapi.controller.cms.role;

import com.mh.core.mhsapi.service.cms.role.RoleCmsServiceImp;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.role.RoleRequest;
import com.mh.core.mhscommons.data.request.role_permission.RolePermissionRequest;
import com.mh.core.mhscommons.data.response.MessageResponse;
import com.mh.core.mhscommons.data.response.role.RoleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/role")
public class RoleCmsController {

    @Autowired
    private RoleCmsServiceImp service;

    @GetMapping("/{id}")
    public ResponseEntity<DfResponse<RoleResponse>> detail(
            @PathVariable("id") Integer id,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.findById(id, authentication));
    }

    @PostMapping("")
    public ResponseEntity<DfResponse<RoleResponse>> insert(
            @RequestBody RoleRequest request,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.insert(request, authentication));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DfResponse<RoleResponse>> update(
            @PathVariable("id") Integer id,
            @RequestBody RoleRequest request,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.update(id, request, authentication));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DfResponse<MessageResponse>> delete(
            @PathVariable("id") Integer id,
            Authentication authentication) {
        return DfResponse.okEntity(service.deleteById(id, authentication));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<RoleResponse>>> search(
            @RequestBody SearchRequest searchRequest) {
        return DfResponse
                .okEntity(service.search(searchRequest));
    }

    @PostMapping("/update-permission")
    public ResponseEntity<DfResponse<MessageResponse>> updatePermission(
            @RequestBody RolePermissionRequest request) {
        return DfResponse
                .okEntity(service.updatePermissions(request));
    }
}
