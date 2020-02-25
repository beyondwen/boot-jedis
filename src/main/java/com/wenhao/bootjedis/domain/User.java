package com.wenhao.bootjedis.domain;

import lombok.Data;

@Data
public class User {

    private int id;

    private int age;

    private String username;

    private String password;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
