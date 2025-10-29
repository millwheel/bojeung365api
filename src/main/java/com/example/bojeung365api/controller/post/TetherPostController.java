package com.example.bojeung365api.controller.post;

import com.example.bojeung365api.dto.post.tether.TetherPostListDto;
import com.example.bojeung365api.dto.post.tether.TetherPostRequest;
import com.example.bojeung365api.dto.post.tether.TetherPostResponse;
import com.example.bojeung365api.security.dto.CustomUserDetails;
import com.example.bojeung365api.service.post.TetherPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/tether")
public class TetherPostController {

    private final TetherPostService tetherPostService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<TetherPostListDto> board(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(required = false, defaultValue = "20") int size) {
        return tetherPostService.getPostListDtos(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TetherPostResponse detail(@PathVariable Long id) {
        return tetherPostService.getPostResponse(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @Valid @RequestBody TetherPostRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        tetherPostService.createPage(request, userDetails.getUsername());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(
            @PathVariable Long id,
            @Valid @RequestBody TetherPostRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        tetherPostService.updatePage(id, request, userDetails.getUsername());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        tetherPostService.deletePage(id, userDetails.getUsername());
    }
}
