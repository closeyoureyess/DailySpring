package com.example.myProject;

import java.util.Objects;

public class Case {
    private int id;
    private static String dateOfCreate;
    private String name;

    Case(int id, String name) {
        this.id = id;
        this.name = name;
    }

    Case() {
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
        Case aCase = (Case) o;
        return id == aCase.id && Objects.equals(name, aCase.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
