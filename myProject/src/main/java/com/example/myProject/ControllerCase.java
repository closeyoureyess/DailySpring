package com.example.myProject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@RestController
@RequestMapping("api/v2/case")
@Slf4j
public class ControllerCase {

    private CaseService localObject = new CaseService();
    private Case localCaseObject = new Case();
    @PostMapping("/create")
    public ResponseEntity<String> createCase(@RequestBody Case caseObject){
        log.info("Логирование");
        System.out.println("Метод POST");
        localObject.createCaseMethod(caseObject.getId(), caseObject.getName());
        return ResponseEntity.ok("Успешно");
    }

    @GetMapping("/gen-info/{id}")
    public ResponseEntity<String> getCase(@PathVariable("id") Integer id){
        log.info("Логирование");
        if(localObject.searchInformation(id)){
            System.out.println("Успешно, метод GET");
            return ResponseEntity.ok("Дата создания: " + localCaseObject.getDateOfCreate() + ";\nНаименование: " + localObject.searchName(id) + ";\nID: " + id);
        } else {
            System.out.println("Не найдено!");
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/update-case/{id}")
    public ResponseEntity<Map<Integer, String>> changeCase(@PathVariable("id") Integer id, @RequestBody Case caseObject){
        log.info("Логирование");
        if(localObject.searchInformation(id)){
            System.out.println("Успешно, метод PATCH ");
            localObject.changeCase(id, caseObject.getName());
            return ResponseEntity.ok(localObject.getCases());
        } else {
            System.out.println("Не найдено!");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<Integer, String>> deleteCase(@PathVariable("id") Integer id){
        log.info("Логирование");
        if(localObject.searchInformation(id)){
            System.out.println("Успешно, метод DELETE");
            localObject.deleteCases(id);
            return ResponseEntity.ok(localObject.getCases());
        } else {
            System.out.println("Не найдено!");
            return ResponseEntity.notFound().build();
        }
    }
}
