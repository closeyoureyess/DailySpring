package com.example.myProject;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class CaseService {
    private int id;
    private String dateOfCreate;
    private String name;
    private static Map<Integer, String> cases = new HashMap<>();

    CaseService(int id, String name) {
        this.id = id;
        this.name = name;
    }
    CaseService() {
    }

    public void createCaseMethod(int id, String name){
        System.out.println("Метод запущен!");
        cases.put(id, name);
    }

    public String searchInformation(int id){
        System.out.println("Метод запущен!");
        return cases.get(id);
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
}
