package com.example.myProject.services;

import com.example.myProject.Case;
import com.example.myProject.CaseRepository;
import com.example.myProject.dto.CaseDto;
import com.example.myProject.mapper.CaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CaseService {
    @Autowired
    private CaseMapper caseMapper;
    @Autowired
    private CaseRepository caseRepository;

    /*@Autowired
    private KafkaSenderService kafkaSenderService;*/

    public CaseDto createCaseMethod(CaseDto caseObject) {
        caseObject.setDateOfCreate(LocalDateTime.now());
       /* kafkaSenderService.sendMessage(caseObject);*/
        Case newCase = caseMapper.convertDtoToCase(caseObject);
        Case newCaseRepository = caseRepository.save(newCase);
        caseObject = caseMapper.convertCaseToDto(newCaseRepository);
        return caseObject;
    }

    public boolean searchInformation(Integer id) {
        if (caseRepository.existsById(id)) {
            return true;
        }
        return false;
    }

    public CaseDto searchName(Integer id) {
        Optional<Case> case1 = caseRepository.findById(id);
        if (case1.isPresent()) {
            return caseMapper.convertCaseToDto(case1.get());
        }
        return null;
    }

    public CaseDto changeCase(CaseDto caseObject) {
        if (caseRepository.findById(caseObject.getId()).isPresent() && searchInformation(caseObject.getId())) {
            Case localObject = caseMapper.convertDtoToCase(caseObject);
            LocalDateTime checkTimePostgre = caseRepository.findById(caseObject.getId()).get().getDateOfCreate();
            if (caseObject.getDateOfCreate() == null && checkTimePostgre != null) {
                localObject.setDateOfCreate(checkTimePostgre);
                return caseMapper.convertCaseToDto(caseRepository.save(localObject));
            }
            return caseMapper.convertCaseToDto(caseRepository.save(localObject));
        }
        return null;
    }

    public boolean deleteCases(Integer id) {
        if (searchInformation(id)) {
            caseRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<CaseDto> getCases(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Case> page = caseRepository.findAll(pageable);
        List<Case> pageToCaseList = page.stream().toList();
        List<CaseDto> caseListPageToCaseDtoList = new LinkedList<>();
        for (int i = 0; i < pageToCaseList.size(); i++) {
            caseListPageToCaseDtoList.add(new CaseDto(pageToCaseList.get(i).getId(), pageToCaseList.get(i).getName(),
                    pageToCaseList.get(i).getDateOfCreate()));
        }
        return caseListPageToCaseDtoList;
    }

    public long getCountAll(Integer integer){

        long result = caseRepository.count();
        return result;
    }

    public void deleteAllCases() {
        caseRepository.deleteAll();
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
