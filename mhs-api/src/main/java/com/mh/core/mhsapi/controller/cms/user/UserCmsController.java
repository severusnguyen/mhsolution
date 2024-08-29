package com.mh.core.mhsapi.controller.cms.user;

import com.mh.core.mhsapi.service.cms.user.UserCmsServiceImp;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.change_password.ChangePasswordRequest;
import com.mh.core.mhscommons.data.request.user_role.UserRoleRequest;
import com.mh.core.mhscommons.data.response.MessageResponse;
import com.mh.core.mhscommons.data.response.user.UserRequest;
import com.mh.core.mhscommons.data.response.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/user")
public class UserCmsController {

    @Autowired
    private UserCmsServiceImp service;

    @GetMapping("/{id}")
    public ResponseEntity<DfResponse<UserResponse>> detail(
            @PathVariable("id") Integer id,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.findById(id, authentication));
    }

    @PostMapping("")
    public ResponseEntity<DfResponse<UserResponse>> insert(
            @RequestBody UserRequest request,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.insert(request,authentication));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DfResponse<UserResponse>> update(
            @PathVariable("id") Integer id,
            @RequestBody UserRequest request,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.update(id, request, authentication));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DfResponse<MessageResponse>> delete(
            @PathVariable("id") Integer id,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.deleteById(id,authentication));
    }

    @PostMapping("/update-role")
    public ResponseEntity<DfResponse<MessageResponse>> updatePermission(
            @RequestBody UserRoleRequest request) {
        return DfResponse
                .okEntity(service.updateRole(request));
    }
    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<UserResponse>>> search(
            @RequestBody SearchRequest searchRequest) {
        return DfResponse
                .okEntity(service.search(searchRequest));
    }
    @PostMapping("/change-password")
    public ResponseEntity<DfResponse<MessageResponse>> changePassword (
            @RequestBody ChangePasswordRequest request) {
        return DfResponse
                .okEntity(service.changePassword(request));
    }
}
