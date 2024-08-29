package com.mh.core.mhsapi.service.cms;


import com.mh.core.mhscommons.data.mappers.BaseMap;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.model.user.UserAuthorDTO;
import com.mh.core.mhscommons.data.response.BaseResponse;
import com.mh.core.mhscommons.data.response.MessageResponse;
import com.mh.core.mhsconfig.config.exception.ApiException;
import com.mh.core.mhsrepository.repository.AbsRepository;
import com.mh.core.mhsrepository.repository.role_permission.RolePermisionRepositoryImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mh.core.mhscommons.data.constant.ActionConstant.*;
import static com.mh.core.mhscommons.data.constant.message.BaseConstant.*;
import static com.mh.core.mhsconfig.utils.SecurityUtils.extractUser;

@Slf4j
public abstract class AbsCmsService<Rq, Rp extends BaseResponse, Po, ID, Repo extends AbsRepository<?, Po, ID>,
        Mp extends BaseMap<Rq, Rp, Po>> implements ICmsService<Rq, Rp, ID> {

    protected Mp mapper;
    protected Repo repository;

    @Autowired
    RolePermisionRepositoryImp rolePermisionRepository;

    @Override
    public Rp insert(Rq request, Authentication authentication) {
        try {
            Set<String> actions = checkPermissionInsert(authentication);
            Po pojo = mapper.toPOJO(request);
            Optional<Po> poOptional = repository.insertReturning(pojo);
            Rp response = mapper.toResponse(poOptional.orElse(null));
            if (response != null) {
                response.setItemPermissions(actions);
                return response;
            } else {
                throw new ApiException("resource not found", 404);
            }
        } catch (ApiException apiException) {
            log.error("[ERROR] insert {} " + apiException.getMessage());
            throw new ApiException(apiException.getMessage(), apiException.getCode());
        }
    }

    @Override
    public Rp update(ID id, Rq request, Authentication authentication) {
        try {
            Set<String> actions = checkPermissionUpdate(authentication);
            Po pojo = mapper.toPOJO(request);
            int isUpdated = repository.update(pojo, id);
            if (isUpdated != 1) {
                throw new ApiException(UPDATE_FAIL);
            } else {
                return getById(id, actions);
            }
        } catch (Exception e) {
            log.error("[ERROR] update {} " + e.getMessage());
            throw new ApiException(UPDATE_FAIL);
        }
    }

    private Rp getById(ID id, Set<String> actions) {
        try {
            Optional<Po> pojoOp = repository.findById(id);
            Rp response = mapper.toResponse(pojoOp.orElse(null));
            if (response != null) {
                response.setItemPermissions(actions);
                return response;
            } else {
                throw new ApiException("resource not found", 404);
            }
        } catch (ApiException apiException) {
            log.error("[ERROR] getById {} " + apiException.getMessage());
            throw new ApiException(apiException.getMessage(), apiException.getCode());
        }
    }

    @Override
    public Rp findById(ID id, Authentication authentication) {
        Set<String> actions = checkPermissionGet(authentication);
        return getById(id, actions);
    }

    @Override
    public MessageResponse deleteById(ID id, Authentication authentication) {
        try {
            Set<String> actions = checkPermissionDelete(authentication);
            Integer isDeleted = repository.delete(id);
            if (isDeleted != 1){
                throw new ApiException(DELETE_FAIL);
            }else{
                MessageResponse messageResponse = new MessageResponse("Success");
                messageResponse.setItemPermissions(actions);
                return messageResponse;
            }
        } catch (Exception e) {
            log.error("[ERROR] deleteById {} " + e.getMessage());
            throw new ApiException(DELETE_FAIL);
        }
    }


    @Override
    public Page<Rp> search(SearchRequest searchRequest) {
        try {
            List<Po> rpo = repository.search(searchRequest);
            List<Rp> rps = mapper.toResponses(rpo);
            Long total = repository.count(searchRequest);
            return new Page<Rp>()
                    .setPage(searchRequest.getPage())
                    .setKey(searchRequest.getKeyword())
                    .setItems(rps)
                    .setTotal(total);
        } catch (Exception e) {
            throw new ApiException(SEARCH_FAIL);
        }
    }

    protected abstract String getPermissionCode();

    protected Set<String> getPermitActions(Authentication authentication) {
        UserAuthorDTO userAuthorDTO = extractUser(authentication);
        return rolePermisionRepository.getPermissions(getPermissionCode() + "%", userAuthorDTO.getRoles())
                .stream()
                .map(s -> {
                    String[] split = s.split("\\.");
                    if (split.length < 2) return null;
                    return split[1];
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    private Set<String> checkPermitAction(String view, String message, Authentication authentication) {
        Set<String> actions = getPermitActions(authentication);
        if (!actions.contains(view)) {
            throw new ApiException(message);
        }
        return actions;
    }

    protected Set<String> checkPermissionGet(Authentication authentication) {
        return checkPermitAction(VIEW, "Bạn không có quyền lấy dữ liệu này.", authentication);
    }

    protected Set<String> checkPermissionInsert(Authentication authentication) {
        return checkPermitAction(ADD, "Bạn không có quyền thêm.", authentication);
    }

    protected Set<String> checkPermissionUpdate(Authentication authentication) {
        return checkPermitAction(UPDATE, "Bạn không có quyền cập nhập.", authentication);
    }

    protected Set<String> checkPermissionDelete(Authentication authentication) {
        return checkPermitAction(DELETE, "Bạn không có quyền xóa.", authentication);
    }


}
