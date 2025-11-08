package com.example.bojeung365api.controller.post;

import com.example.bojeung365api.dto.post.official.OfficialPostListDto;
import com.example.bojeung365api.dto.post.official.OfficialPostRequest;
import com.example.bojeung365api.dto.post.official.OfficialPostResponse;
import com.example.bojeung365api.service.post.OfficialPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/official")
public class OfficialPostController {

    private final OfficialPostService officialPostService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<OfficialPostListDto> board(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(required = false, defaultValue = "20") int size) {
        return officialPostService.getPostListDtos(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OfficialPostResponse detail(@PathVariable Long id,
                                       @AuthenticationPrincipal UserDetails userDetails) {
        return officialPostService.getPostResponse(id, userDetails);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @Valid @RequestBody OfficialPostRequest request,
            @AuthenticationPrincipal UserDetails userDetails
            ) {
        officialPostService.createPage(request, userDetails.getUsername());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(
            @PathVariable Long id,
            @Valid @RequestBody OfficialPostRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        officialPostService.updatePage(id, request, userDetails.getUsername());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        officialPostService.deletePage(id, userDetails.getUsername());
    }
}
