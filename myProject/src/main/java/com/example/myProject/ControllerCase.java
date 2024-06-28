package com.example.myProject;

import com.example.myProject.CaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v2/case")
@Slf4j
public class ControllerCase {

    private CaseService localObject;
    @PostMapping("/create")
    public ResponseEntity<String> createCase(@RequestBody CaseService caseService){
        log.info("Логирование");
        System.out.println("Контроллер");
        caseService.createCaseMethod(caseService.getId(), caseService.getName());
        return ResponseEntity.ok("Успешно");
    }

    @GetMapping("/gen-info/{id}")
    public ResponseEntity<String> getCase(@PathVariable("id") Integer id){
        return ResponseEntity.ok(localObject.getDateOfCreate() + ", " + localObject.searchInformation(id));
    }

}
