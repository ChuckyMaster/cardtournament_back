package com.mary_tournament.tournament.security;

import com.mary_tournament.tournament.model.User;
import com.mary_tournament.tournament.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;
import java.util.stream.Collectors;


import java.util.Collections;

@Service
public class UserCustomDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserCustomDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())) // Assure-toi que ROLE_ est bien ajouté ici
                .collect(Collectors.toList());
        System.out.println("✅ Utilisateur trouvé : " + user.getEmail() + " | Rôles : " + user.getRoles());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities  // 🔹 Charge les rôles ici
        );
    }

}


