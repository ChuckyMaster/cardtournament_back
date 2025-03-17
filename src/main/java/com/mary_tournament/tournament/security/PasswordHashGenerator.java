package com.mary_tournament.tournament.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode("123456"); // Mot de passe à encoder
        System.out.println("Mot de passe hashé : " + hashedPassword);
    }
}
