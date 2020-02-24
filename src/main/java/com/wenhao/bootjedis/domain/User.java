package com.wenhao.bootjedis.domain;

import lombok.Data;

@Data
public class User {

    private int age;

    private String username;

    private String password;

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
