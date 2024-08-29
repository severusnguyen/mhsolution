package com.mh.core.mhsapi.controller.api.news;

import com.mh.core.mhsapi.service.api.news.NewService;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.response.news.NewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public/news")
public class NewController {

    @Autowired
    private NewService newService;

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<NewResponse>>> search(@RequestBody SearchRequest searchRequest){
        return DfResponse
                .okEntity(newService.searchNew(searchRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DfResponse<NewResponse>> detail(@PathVariable("id") Integer id){
        return DfResponse
                .okEntity(newService.findById(id));
    }

    @PostMapping("/select")
    public ResponseEntity<DfResponse<Page<NewResponse>>> select(@RequestBody SearchRequest searchRequest){
        return DfResponse
                .okEntity(newService.select(searchRequest));
    }
}
