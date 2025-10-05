package com.example.bojeung365api.controller.post;

import com.example.bojeung365api.dto.post.review.ReviewPostListDto;
import com.example.bojeung365api.dto.post.review.ReviewPostRequest;
import com.example.bojeung365api.dto.post.review.ReviewPostResponse;
import com.example.bojeung365api.security.dto.CustomUserDetails;
import com.example.bojeung365api.service.post.ReviewPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/review")
public class ReviewPostController {

    private final ReviewPostService reviewPostService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ReviewPostListDto> board(@RequestParam(defaultValue = "0") int page) {
        return reviewPostService.getPostListDtos(page);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReviewPostResponse detail(@PathVariable Long id) {
        return reviewPostService.getPostResponse(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @Valid @RequestBody ReviewPostRequest request,
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {
        reviewPostService.createPage(request, customUserDetails.getId());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(
            @PathVariable Long id,
            @Valid @RequestBody ReviewPostRequest request,
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {
        reviewPostService.updatePage(id, request, customUserDetails.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails me
    ) {
        reviewPostService.deletePage(id, me.getId());
    }
}
