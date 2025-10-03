package com.example.bojeung365api.service;

import com.example.bojeung365api.entity.user.User;
import com.example.bojeung365api.entity.user.UserRole;
import com.example.bojeung365api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(String username, String rawPassword, String email, String nickname) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("이미 존재하는 사용자명입니다.");
        }
        if (userRepository.existsByNickname(nickname)) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }

        String encoded = passwordEncoder.encode(rawPassword);
        User user = new User(username, encoded, email, nickname, UserRole.MEMBER);
        userRepository.save(user);
    }

}
