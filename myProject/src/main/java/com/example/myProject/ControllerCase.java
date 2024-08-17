package com.example.myProject;

import com.example.myProject.dto.CaseDto;
import com.example.myProject.services.CaseService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@RequestMapping("api/v2/case")
@Slf4j
public class ControllerCase {

    @Autowired
    private CaseService caseService;

    @PostMapping("/create")
    public ResponseEntity<CaseDto> createCase(@RequestBody CaseDto caseObject) {
        log.info("Создание дела, POST " + caseObject.getName());
        CaseDto caseDto = caseService.createCaseMethod(caseObject);
        if (caseDto != null) {
            return ResponseEntity.ok(caseDto);
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/gen-info/{id}")
    public ResponseEntity<CaseDto> getCase(@PathVariable("id") Integer id) {
        log.info("Получение дела по id, метод GET " + id);
        CaseDto caseDto = caseService.searchName(id);
        if (caseDto != null) {
            return ResponseEntity.ok(caseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all-entity/{page}/{quantity}")
    public ResponseEntity<List<CaseDto>> getAllCase(@PathVariable("page") Integer pageNubmer,
                                                    @PathVariable("quantity") @Min(0) @Max(30) Integer pageSize) {
        List<CaseDto> result = caseService.getCases(pageNubmer, pageSize);
        if (!result.isEmpty()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/all-entity/counts")
    public ResponseEntity<CaseDto> getCountAllCase(@PathVariable("id") Integer integer){

    }

    @PatchMapping("/update-case")
    public ResponseEntity<CaseDto> changeCase(@RequestBody CaseDto caseObject) {
        log.info("Изменения дела по id, метод PATCH " + caseObject.getName() + " " + caseObject.getId());
        CaseDto caseDto = caseService.changeCase(caseObject);
        if (caseDto != null) {
            return ResponseEntity.ok(caseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<CaseDto>> deleteCase(@PathVariable("id") Integer id) {
        log.info("Удаление дела по id, метод DELETE " + id);
        boolean methodResult = caseService.deleteCases(id);
        if (methodResult) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteCase() {
        log.info("Удаление всех записей, DELETE-ALL ");
        caseService.deleteAllCases();
        return ResponseEntity.ok("Успешно");
    }
}
