package com.mary_tournament.tournament.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDTO {
    private Long id;
    private String login;
    private String name;
    private String email;
    private String password;
    private Integer totalPoints;
}
