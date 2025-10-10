package com.example.bojeung365api.controller;

import com.example.bojeung365api.dto.user.ChangePasswordRequest;
import com.example.bojeung365api.dto.user.MeResponse;
import com.example.bojeung365api.service.MeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('MEMBER')")
    public MeResponse getMe(@AuthenticationPrincipal UserDetails userDetails) {
        return meService.getMe(userDetails.getUsername());
    }

    @PostMapping("/change-password")
    public void changePassword(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody ChangePasswordRequest request
    ) {
        meService.changePassword(userDetails.getUsername(), request.currentPassword(), request.newPassword());
    }
}
