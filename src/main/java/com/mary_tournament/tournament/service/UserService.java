package com.mary_tournament.tournament.service;

import com.mary_tournament.tournament.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> getAllUsers();
    Optional<UserDTO> getUserById(Long id);

    Optional<UserDTO> getUserById(Integer id);

    UserDTO createUser(UserDTO userDTO);
    void deleteUser(Long id);

    void deleteUser(Integer id);
}
