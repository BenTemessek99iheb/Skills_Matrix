package com.elyadata.sm.dto;

import com.elyadata.sm.model.ERole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private UUID id;
    private String username;
    private String email;
    private ERole roles;
    private String profilePicture_url;
    private String gender;
    private String description;
    private Integer level;
    private Integer exp;
    private String Job;



}
