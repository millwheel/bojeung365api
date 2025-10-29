package com.example.bojeung365api.util;

import com.example.bojeung365api.entity.user.User;
import com.example.bojeung365api.exception.custom.InvalidAuthorityException;

public final class AuthorityValidator {

    public static void validateMySelf(User existingAuthor, String username) {
        if (!existingAuthor.getUsername().equals(username)) {
            throw new InvalidAuthorityException();
        }
    }

}
