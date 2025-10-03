package com.example.bojeung365api.controller;

import lombok.RequiredArgsConstructor;
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
