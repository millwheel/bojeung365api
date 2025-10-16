package com.example.bojeung365api.controller.post;

import com.example.bojeung365api.dto.post.notice.NoticePostListDto;
import com.example.bojeung365api.dto.post.notice.NoticePostRequest;
import com.example.bojeung365api.dto.post.notice.NoticePostResponse;
import com.example.bojeung365api.security.dto.CustomUserDetails;
import com.example.bojeung365api.service.post.NoticePostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/notice")
public class NoticePostController {

    private final NoticePostService noticePostService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<NoticePostListDto> board(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(required = false, defaultValue = "20") int size) {
        return noticePostService.getPostListDtos(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NoticePostResponse detail(@PathVariable Long id) {
        return noticePostService.getPostResponse(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @Valid @RequestBody NoticePostRequest request,
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {
        noticePostService.createPage(request, customUserDetails.getId());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(
            @PathVariable Long id,
            @Valid @RequestBody NoticePostRequest request,
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {
        noticePostService.updatePage(id, request, customUserDetails.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails me
    ) {
        noticePostService.deletePage(id, me.getId());
    }
}
