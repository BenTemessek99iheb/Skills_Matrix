package com.elyadata.sm.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillDTO {

    private UUID id;
    private String name;
    private String description;
    private int lvl;
    private Instant createdAt;
    private UUID categoryId;

}
