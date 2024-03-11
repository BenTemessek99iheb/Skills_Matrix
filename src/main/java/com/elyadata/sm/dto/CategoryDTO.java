package com.elyadata.sm.dto;

import lombok.Data;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Data
public class CategoryDTO {

    private UUID id;

    private String name;

    private Set<SkillDTO> skills;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDTO that = (CategoryDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
