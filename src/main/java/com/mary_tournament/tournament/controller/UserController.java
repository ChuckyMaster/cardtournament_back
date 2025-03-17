package com.mary_tournament.tournament.controller;

import com.mary_tournament.tournament.dto.UserDTO;
import com.mary_tournament.tournament.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "API de gestion des utilisateurs")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Récupérer tous les utilisateurs")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un utilisateur par ID")
    public UserDTO getUserById(@PathVariable Integer id) {
        return userService.getUserById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping
    @Operation(summary = "Créer un nouvel utilisateur")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un utilisateur")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
