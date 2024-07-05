package com.example.myProject;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

@Service
public class CaseService {
    private static Map<Integer, Case> cases = new HashMap<>();

    public Case createCaseMethod(Case caseObject) {
        LocalDateTime localDateTime = LocalDateTime.now();
        caseObject.setDateOfCreate(localDateTime.toLocalTime().toString());
        cases.put(caseObject.getId(), new Case(caseObject.getName(), caseObject.getDateOfCreate()));
        return caseObject;
    }

    public boolean searchInformation(int id) {
        if (cases.containsKey(id)) {
            return true;
        } else {
            return false;
        }
    }

    public Case searchName(int id) {
        return cases.get(id);
    }

    public void changeCase(int id, Case caseObject) {
        HashMap<Integer, Case> forReplace = new HashMap<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        caseObject.setDateOfCreate(localDateTime.toLocalTime().toString());
        for (int key : cases.keySet()) {
            if (id == key) {
                forReplace.put(id, new Case(caseObject.getName(), caseObject.getDateOfCreate()));
                break;
            }
        }
        for (Map.Entry<Integer, Case> enumeration : cases.entrySet()) {
            if (id != enumeration.getKey()) {
                forReplace.put(enumeration.getKey(), new Case(enumeration.getValue().getName(), enumeration.getValue().getDateOfCreate()));
            }
        }
        cases.clear();
        cases.putAll(forReplace);
    }

    public void deleteCases(int id){
        cases.remove(id);
    }

    public Map<Integer, Case> getCases() {
        return cases;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
