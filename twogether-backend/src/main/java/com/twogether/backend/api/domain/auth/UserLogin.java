package com.twogether.backend.api.domain.auth;

import com.google.common.base.Strings;

public class UserLogin {
    public String login;
    public String password;

    public boolean isValidated() {
        return !Strings.isNullOrEmpty(login) && !Strings.isNullOrEmpty(password);
    }
}
