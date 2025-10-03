package com.example.bojeung365api.controller;

import com.example.bojeung365api.entity.User;
import com.example.bojeung365api.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Deprecated
@RestController
@RequiredArgsConstructor
public class LoginController {
//
//    private final AuthenticationManager authenticationManager;
//    private final SecurityContextRepository securityContextRepository;
//    private final UserRepository userRepository;
//
//    // DTO
//    public record LoginRequest(String username, String password) {}
//    public record SessionUserResponse(Long id, String username, String nickname, String email) {
//        public static SessionUserResponse from(User u) {
//            return new SessionUserResponse(u.getId(), u.getUsername(), u.getNickname(), u.getEmail());
//        }
//    }
//
//    /** 로그인: JSON 바디 → 세션에 SecurityContext 저장 → 사용자 정보 반환 */
//    @PostMapping("/login")
//    public ResponseEntity<SessionUserResponse> login(@RequestBody LoginRequest req,
//                                                     HttpServletRequest httpRequest,
//                                                     HttpServletResponse httpResponse) {
//        UsernamePasswordAuthenticationToken authToken =
//                new UsernamePasswordAuthenticationToken(req.username(), req.password());
//
//        Authentication authentication = authenticationManager.authenticate(authToken);
//
//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//        context.setAuthentication(authentication);
//
//        // 세션에 SecurityContext 저장
//        securityContextRepository.saveContext(context, httpRequest, httpResponse);
//
//        // 사용자 요약 반환
//        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
//        return ResponseEntity.ok(SessionUserResponse.from(user));
//    }
//

}
