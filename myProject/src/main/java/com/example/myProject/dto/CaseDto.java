package com.example.myProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CaseDto {

    @JsonProperty(access =  JsonProperty.Access.READ_ONLY)
    private int id;
    @JsonProperty(access =  JsonProperty.Access.READ_ONLY)
    private static String dateOfCreate;
    private String name;

    public CaseDto(int id, String name, String dateOfCreate) {
        this.name = name;
    }

    public CaseDto(String name, String dateOfCreate) {
        this.name = name;
    }

    public CaseDto(String name) {
        this.name = name;
    }

    public CaseDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(String dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaseDto caseDto = (CaseDto) o;
        return id == caseDto.id && Objects.equals(name, caseDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
