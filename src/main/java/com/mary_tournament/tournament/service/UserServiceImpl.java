package com.mary_tournament.tournament.service;

import com.mary_tournament.tournament.dto.UserDTO;
import com.mary_tournament.tournament.model.User;
import com.mary_tournament.tournament.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Ajout de PasswordEncoder

    // Convertir User en UserDTO
    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setTotalPoints(user.getTotalPoints());
        return userDTO;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> getUserById(Integer id) {
        return userRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        // Hachage du mot de passe avant l'enregistrement
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        user.setTotalPoints(userDTO.getTotalPoints());
        user = userRepository.save(user);
        return convertToDTO(user);
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
