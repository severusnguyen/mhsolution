package com.mh.core.mhsapi.controller.api.home_page;

import com.mh.core.mhsapi.service.api.home_page.IHomePageService;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.response.home_page.HomePageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public/home-page")
public class HomePageController {

    private IHomePageService service;

    @Autowired
    public HomePageController(IHomePageService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DfResponse<HomePageResponse>> detail(
            @PathVariable("id") int id
    ) {
        return DfResponse
                .okEntity(service.findById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<HomePageResponse>>> search(
            @RequestBody SearchRequest searchRequest
    ) {
        return DfResponse
                .okEntity(service.search(searchRequest));
    }

    @PostMapping("/select")
    public ResponseEntity<DfResponse<Page<HomePageResponse>>> select(
            @RequestBody SearchRequest searchRequest
    ) {
        return DfResponse
                .okEntity(service.select(searchRequest));
    }

}
