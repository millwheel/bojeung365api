package com.example.bojeung365api.controller.post;

import com.example.bojeung365api.dto.post.scam.ScamReportPostListDto;
import com.example.bojeung365api.dto.post.scam.ScamReportPostRequest;
import com.example.bojeung365api.dto.post.scam.ScamReportPostResponse;
import com.example.bojeung365api.service.post.ScamReportPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/scam-report")
public class ScamReportPostController {

    private final ScamReportPostService scamReportPostService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ScamReportPostListDto> board(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(required = false, defaultValue = "20") int size) {
        return scamReportPostService.getPostListDtos(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ScamReportPostResponse detail(@PathVariable Long id,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        return scamReportPostService.getPostResponse(id, userDetails);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @Valid @RequestBody ScamReportPostRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        scamReportPostService.createPage(request, userDetails.getUsername());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(
            @PathVariable Long id,
            @Valid @RequestBody ScamReportPostRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        scamReportPostService.updatePage(id, request, userDetails.getUsername());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        scamReportPostService.deletePage(id, userDetails.getUsername());
    }
}
