package com.example.myProject;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.aspectj.weaver.tools.GeneratedClassHandler;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "case_entity")
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date_of_create")
    private LocalDateTime dateOfCreate;

    @Column(name = "name")
    private String name;

    public Case(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Case(int id, String name, LocalDateTime dateOfCreate) {
        this.id = id;
        this.name = name;
        this.dateOfCreate = dateOfCreate;
    }

    public Case(String name, LocalDateTime dateOfCreate) {
        this.name = name;
        this.dateOfCreate = dateOfCreate;
    }

    public Case(String name) {
        this.name = name;
    }

    public Case() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(LocalDateTime dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Case aCase = (Case) o;
        return id == aCase.id && Objects.equals(name, aCase.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
