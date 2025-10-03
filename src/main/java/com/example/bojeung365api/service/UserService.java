package com.example.bojeung365api.service;

import com.example.bojeung365api.entity.User;
import com.example.bojeung365api.entity.UserRole;
import com.example.bojeung365api.exception.custom.UserNotFoundException;
import com.example.bojeung365api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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

    public void changePassword(String username, String currentRawPassword, String newRawPassword) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(currentRawPassword, user.getPassword())) {
            throw new IllegalArgumentException("현재 비밀번호가 올바르지 않습니다.");
        }

        String encoded = passwordEncoder.encode(newRawPassword);
        user.updatePassword(encoded);
    }

}
