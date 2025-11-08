package com.example.bojeung365api.util;

import com.example.bojeung365api.entity.user.User;
import com.example.bojeung365api.entity.user.UserRole;
import com.example.bojeung365api.exception.custom.InvalidAuthorityException;

public final class AuthorityValidator {

    public static boolean checkEditable(User author, User requestUser) {
        if (requestUser.getRole().equals(UserRole.ADMIN)) {
            return true;
        }
        return author.getUsername().equals(requestUser.getUsername());
    }

    public static void validateEditable(User author, User requestUser) {
        if (requestUser.getRole().equals(UserRole.ADMIN)) {
            return;
        }
        if (!author.getUsername().equals(requestUser.getUsername())) {
            throw new InvalidAuthorityException();
        }
    }

}
