package com.example.myProject;

import org.springframework.stereotype.Repository;

@Repository
public class CaseRepository {
    public void rep(){
        System.out.println("Репозиторий");
    }

    public Case createCase(Case caseObject){
        return new Case();
    }
}
