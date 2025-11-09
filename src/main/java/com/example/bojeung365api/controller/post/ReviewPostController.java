package com.example.bojeung365api.controller.post;

import com.example.bojeung365api.dto.post.review.ReviewPostListDto;
import com.example.bojeung365api.dto.post.review.ReviewPostRequest;
import com.example.bojeung365api.dto.post.review.ReviewPostResponse;
import com.example.bojeung365api.service.post.ReviewPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/review")
public class ReviewPostController {

    private final ReviewPostService reviewPostService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ReviewPostListDto> board(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(required = false, defaultValue = "20") int size) {
        return reviewPostService.getPostListDtos(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReviewPostResponse detail(@PathVariable Long id,
                                     @AuthenticationPrincipal UserDetails userDetails) {
        return reviewPostService.getPostResponse(id, userDetails);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @Valid @RequestBody ReviewPostRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        reviewPostService.createPage(request, userDetails.getUsername());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(
            @PathVariable Long id,
            @Valid @RequestBody ReviewPostRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        reviewPostService.updatePage(id, request, userDetails.getUsername());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        reviewPostService.deletePage(id, userDetails.getUsername());
    }
}
