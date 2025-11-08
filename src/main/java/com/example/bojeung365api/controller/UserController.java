package com.example.bojeung365api.controller;

import com.example.bojeung365api.dto.user.ChangePasswordRequest;
import com.example.bojeung365api.dto.user.MeResponse;
import com.example.bojeung365api.dto.user.PasswordResetRequest;
import com.example.bojeung365api.dto.user.PasswordResetResponse;
import com.example.bojeung365api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    @PreAuthorize("hasRole('MEMBER')")
    public MeResponse getMe(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.getMe(userDetails.getUsername());
    }

    @PutMapping("/password")
    public void changePassword(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody ChangePasswordRequest request
    ) {
        userService.changePassword(userDetails.getUsername(), request.currentPassword(), request.newPassword());
    }

    @PostMapping("/password/reset")
    public PasswordResetResponse resetPassword(@Valid @RequestBody PasswordResetRequest request) {
        String generatedPassword = userService.resetPasswordWithRandom(request.nickname(), request.email());
        return new PasswordResetResponse(generatedPassword);
    }
}
