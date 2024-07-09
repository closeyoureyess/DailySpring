package com.example.myProject;

import com.example.myProject.dto.CaseDto;
import com.example.myProject.mapper.CaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CaseService {
    @Autowired
    CaseMapper caseMapper;
    @Autowired
    CaseRepository caseRepository;

    public CaseDto createCaseMethod(CaseDto caseObject) {
        LocalDateTime localDateTime = LocalDateTime.now();
        caseObject.setDateOfCreate(localDateTime.toLocalTime().toString());
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
        if(caseRepository.findById(caseObject.getId()).isPresent()){
            return caseMapper.convertCaseToDto(caseRepository.save(caseMapper.convertDtoToCase(caseObject)));
        }
        return null;
    }

    public boolean deleteCases(Integer id) {
        caseRepository.deleteById(id);
        if(caseRepository.findById(id).isPresent()){
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
