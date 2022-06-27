package com.example.schoolsite.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SignUpRequest {
    private String name;
    private String lastname;
    private String patronymic;
    private String email;
    private String login;
    private String password;
    private Set<String> role;
    private String status;
    private String link;
}
