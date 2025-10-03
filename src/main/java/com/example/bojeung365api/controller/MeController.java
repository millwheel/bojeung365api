package com.example.bojeung365api.controller;

import com.example.bojeung365api.dto.user.ChangePasswordRequest;
import com.example.bojeung365api.dto.user.MeResponse;
import com.example.bojeung365api.service.MeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/me")
public class MeController {

    private final MeService meService;

    @GetMapping
    public MeResponse getMe(Authentication authentication) {
        return meService.getMe(authentication.getName());
    }

    @PostMapping("/change-password")
    public void changePassword(
            @AuthenticationPrincipal UserDetails principal,
            @Valid @RequestBody ChangePasswordRequest request
    ) {
        meService.changePassword(principal.getUsername(), request.currentPassword(), request.newPassword());
    }
}
