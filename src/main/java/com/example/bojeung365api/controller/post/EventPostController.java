package com.example.bojeung365api.controller.post;

import com.example.bojeung365api.dto.post.event.EventPostListDto;
import com.example.bojeung365api.dto.post.event.EventPostRequest;
import com.example.bojeung365api.dto.post.event.EventPostResponse;
import com.example.bojeung365api.security.dto.CustomUserDetails;
import com.example.bojeung365api.service.post.EventPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/event")
public class EventPostController {

    private final EventPostService eventPostService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<EventPostListDto> board(@RequestParam(defaultValue = "0") int page) {
        return eventPostService.getPostListDtos(page);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventPostResponse detail(@PathVariable Long id) {
        return eventPostService.getPostResponse(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @Valid @RequestBody EventPostRequest request,
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {
        eventPostService.createPage(request, customUserDetails.getId());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(
            @PathVariable Long id,
            @Valid @RequestBody EventPostRequest request,
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {
        eventPostService.updatePage(id, request, customUserDetails.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails me
    ) {
        eventPostService.deletePage(id, me.getId());
    }
}
