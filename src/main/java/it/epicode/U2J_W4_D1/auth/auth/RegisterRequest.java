package it.epicode.U2J_W4_D1.auth.auth;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String email;
}
