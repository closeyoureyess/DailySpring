package com.example.myProject;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CaseService {
    private static Map<Integer, String> cases = new HashMap<>();

    public void createCaseMethod(int id, String name) {
        cases.put(id, name);
    }

    public boolean searchInformation(int id) {
        if (cases.containsKey(id)) {
            return true;
        } else {
            return false;
        }
    }

    public String searchName(int id) {
        return cases.get(id);
    }

    public void changeCase(int id, String name) {
        HashMap<Integer, String> forReplace = new HashMap<>();
        for (int key : cases.keySet()) {
            if (id == key) {
                forReplace.put(id, name);
                break;
            }
        }
        for (Map.Entry<Integer, String> enumeration : cases.entrySet()) {
            if (id != enumeration.getKey()) {
                forReplace.put(enumeration.getKey(), enumeration.getValue());
            }
        }
        cases.clear();
        cases.putAll(forReplace);

    }

    public void deleteCases(int id){
        cases.remove(id);
    }

    public Map<Integer, String> getCases() {
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
