package dev.springauth.springauth.DTO;

import java.util.Set;

import lombok.Data;

@Data
public class SignupDTO {
    private String username;
    private String password;
    private String email;
    private Set<String> roles;
}
