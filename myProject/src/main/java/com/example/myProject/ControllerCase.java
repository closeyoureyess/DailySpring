package com.example.myProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@RestController
@RequestMapping("api/v2/case")
@Slf4j
public class ControllerCase {

    @Autowired
    private CaseService localObject;
    @Autowired
    private Case localCaseObject;

    @PostMapping("/create")
    public ResponseEntity<String> createCase(@RequestBody Case caseObject) {
        log.info("Создание дела, POST");
        if(localObject.createCaseMethod(caseObject) != null) {
            return ResponseEntity.ok("Успешно");
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/gen-info/{id}")
    public ResponseEntity<String> getCase(@PathVariable("id") Integer id) {
        log.info("Получение дела по id, метод GET");
        if (localObject.searchInformation(id)) {
            System.out.println("Успешно, метод GET");
            return ResponseEntity.ok("Дата создания: " + localObject.searchName(id).getDateOfCreate() + ";\nНаименование: " + localObject.searchName(id).getName() + ";\nID: " + id);
        } else {
            System.out.println("Не найдено!");
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/update-case/{id}")
    public ResponseEntity<Map<Integer, Case>> changeCase(@PathVariable("id") Integer id, @RequestBody Case caseObject) {
        log.info("Изменения дела по id, метод PATCH");
        if (localObject.searchInformation(id)) {
            localObject.changeCase(id, caseObject);
            return ResponseEntity.ok(localObject.getCases());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<Integer, Case>> deleteCase(@PathVariable("id") Integer id) {
        log.info("Удаление дела по id, метод DELETE");
        if (localObject.searchInformation(id)) {
            localObject.deleteCases(id);
            return ResponseEntity.ok(localObject.getCases());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
