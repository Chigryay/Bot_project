package org.example;

import java.util.HashMap;
import java.util.Map;

public class Users {
    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    private String password;
    private String login;

    public Users (String login, String password) {
        this.password = password;
        this.login = login;
    }

    Map<String, String> users = new HashMap<>();

}
