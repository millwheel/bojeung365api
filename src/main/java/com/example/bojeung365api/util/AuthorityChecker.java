package com.example.bojeung365api.util;

import com.example.bojeung365api.entity.user.User;
import com.example.bojeung365api.entity.user.UserRole;
import com.example.bojeung365api.exception.custom.InvalidAuthorityException;
import org.springframework.security.core.userdetails.UserDetails;

public final class AuthorityChecker {

    public static void validateEditable(User author, User requestUser) {
        if (requestUser.getRole().equals(UserRole.ADMIN)) {
            return;
        }
        if (!author.getUsername().equals(requestUser.getUsername())) {
            throw new InvalidAuthorityException();
        }
    }

    public static boolean checkEditable(User author, UserDetails userDetails) {
        if (userDetails == null) return false;

        boolean isAdmin = userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return true;
        }

        return author.getUsername().equals(userDetails.getUsername());
    }


}
