package com.example.bojeung365api.controller.post;

import com.example.bojeung365api.dto.post.scam.ScamReportPostListDto;
import com.example.bojeung365api.dto.post.scam.ScamReportPostRequest;
import com.example.bojeung365api.dto.post.scam.ScamReportPostResponse;
import com.example.bojeung365api.security.dto.CustomUserDetails;
import com.example.bojeung365api.service.post.ScamReportPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/scam-report")
public class ScamReportPostController {

    private final ScamReportPostService scamReportPostService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ScamReportPostListDto> board(@RequestParam(defaultValue = "0") int page) {
        return scamReportPostService.getPostListDtos(page);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ScamReportPostResponse detail(@PathVariable Long id) {
        return scamReportPostService.getPostResponse(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @Valid @RequestBody ScamReportPostRequest request,
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {
        scamReportPostService.createPage(request, customUserDetails.getId());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(
            @PathVariable Long id,
            @Valid @RequestBody ScamReportPostRequest request,
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {
        scamReportPostService.updatePage(id, request, customUserDetails.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails me
    ) {
        scamReportPostService.deletePage(id, me.getId());
    }
}
