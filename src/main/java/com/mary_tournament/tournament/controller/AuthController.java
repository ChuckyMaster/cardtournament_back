package com.mary_tournament.tournament.controller;

import com.mary_tournament.tournament.dto.AuthResponseDTO;
import com.mary_tournament.tournament.dto.UserCredentialsDTO;
import com.mary_tournament.tournament.dto.UserDTO;
import com.mary_tournament.tournament.model.User;
import com.mary_tournament.tournament.repository.UserRepository;
import com.mary_tournament.tournament.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/accounts")
@CrossOrigin(origins = { "http://localhost:5173", "http://127.0.0.1:5173", "http://localhost:8080", "http://127.0.0.1:8000" })

public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDto) {

        if (userDto.getLogin() == null || userDto.getLogin().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Erreur : le champ 'login' est requis !");
        }

        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("❌ Cet email est déjà utilisé.");
        }
        User newUser = new User();
        newUser.setLogin(userDto.getLogin()); //
        newUser.setName(userDto.getName());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setTotalPoints(userDto.getTotalPoints() != null ? userDto.getTotalPoints() : 0);
        newUser.setEnabled(true);

        userRepository.save(newUser);
        return ResponseEntity.ok("✅ Inscription réussie !");
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserCredentialsDTO credentials) {
        System.out.println("✅ LOGIN REÇU : " + credentials.getLogin());
        System.out.println("✅ PASSWORD REÇU : " + credentials.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.getLogin(), credentials.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);

        // Récupération de l'utilisateur en base de données
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        //  Retourner id + name + token
        return ResponseEntity.ok(new AuthResponseDTO(token, user.getName(), user.getId()));
    }

}
