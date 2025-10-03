package com.example.bojeung365api.service;

import com.example.bojeung365api.dto.user.MeResponse;
import com.example.bojeung365api.entity.user.User;
import com.example.bojeung365api.exception.custom.UserNotFoundException;
import com.example.bojeung365api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MeService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public MeResponse getMe(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return new MeResponse(user);
    }

    @Transactional
    public void changePassword(String username, String currentRawPassword, String newRawPassword) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(currentRawPassword, user.getPassword())) {
            throw new IllegalArgumentException("현재 비밀번호가 올바르지 않습니다.");
        }

        String encoded = passwordEncoder.encode(newRawPassword);
        user.updatePassword(encoded);
    }

}
