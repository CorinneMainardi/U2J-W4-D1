package it.epicode.U2J_W4_D1.auth.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class AuthRunner implements ApplicationRunner {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Creazione dell'utente admin se non esiste
        Optional<AppUser> adminUser = appUserService.findByUsername("admin");
        if (adminUser.isEmpty()) {
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setName("nome");
            registerRequest.setLastname("cognome");
            registerRequest.setEmail("email@email.it");
            registerRequest.setUsername("username");
            registerRequest.setPassword("userpwd");
        }

        // Creazione dell'utente user se non esiste
        Optional<AppUser> normalUser = appUserService.findByUsername("user");
        if (normalUser.isEmpty()) {
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setName("nome");
            registerRequest.setLastname("cognome");
            registerRequest.setEmail("email@email.it");
            registerRequest.setUsername("username");
            registerRequest.setPassword("userpwd");
        }
    }
}
