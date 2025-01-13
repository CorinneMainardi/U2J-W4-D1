package it.epicode.U2J_W4_D1.auth.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
