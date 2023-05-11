package com.firesuits.server.domain.learn.controller;

import com.firesuits.server.domain.content.dto.response.ContentResponse;
import com.firesuits.server.domain.learn.dto.LearnDto;
import com.firesuits.server.domain.learn.dto.request.LearnCreateRequest;
import com.firesuits.server.domain.learn.dto.request.LearnUpdateRequest;
import com.firesuits.server.domain.learn.dto.response.LearnResponse;
import com.firesuits.server.domain.learn.service.LearnService;

import com.firesuits.server.global.error.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Validated
@Slf4j
@RequestMapping("/contents")
public class LearnController {
    private final LearnService learnService;

    public LearnController(LearnService learnService) {
        this.learnService = learnService;
    }

    @PostMapping("/{contentId}/learns")
    public Response<Void> create(@PathVariable Long contentId,
                                 @RequestBody LearnCreateRequest request, Authentication authentication){
        learnService.create(request.getTitle(), request.getContent(), authentication.getName(), contentId);
        return Response.success();
    }

    @PatchMapping("/{contentId}/learns/{learnId}")
    public Response<LearnResponse> update(@PathVariable Long contentId,
                                          @PathVariable Long learnId,
                                          @RequestBody LearnUpdateRequest request,
                                          Authentication authentication){
        LearnDto learnDto = learnService.update(request.getTitle(), request.getContent(), authentication.getName(), contentId, learnId);
        return Response.success(LearnResponse.from(learnDto));
    }

    @DeleteMapping("/{contentId}/learns/{learnId}")
    public Response<Void> delete(@PathVariable Long contentId,
                                 @PathVariable Long learnId,
                                 Authentication authentication){
        learnService.delete(authentication.getName(), contentId, learnId);
        return Response.success();
    }

    @GetMapping("/{contentId}/learns/{learnId}")
    public Response<LearnResponse> get(@PathVariable Long contentId,
                                       @PathVariable Long learnId){
        LearnDto learnDto = learnService.findById(contentId, learnId);
        return Response.success(LearnResponse.from(learnDto));
    }

    @GetMapping("/{contentId}/learns")
    public Response<Page<LearnResponse>> list(@PathVariable Long contentId, Pageable pageable){
        return Response.success(learnService.list(contentId, pageable).map(LearnResponse::from));
    }

}
