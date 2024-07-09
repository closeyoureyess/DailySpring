package com.example.myProject.mapper;

import com.example.myProject.Case;
import com.example.myProject.dto.CaseDto;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class CaseMapper {

    public Case convertDtoToCase(CaseDto caseDto) {
        Case caseLocalObject = new Case();
        caseLocalObject.setDateOfCreate(caseDto.getDateOfCreate());
        caseLocalObject.setName(caseDto.getName());
        caseLocalObject.setId(caseDto.getId());
        return caseLocalObject;
    }

    public CaseDto convertCaseToDto(Case caseObject) {
        CaseDto caseDto = new CaseDto();
        caseDto.setDateOfCreate(caseObject.getDateOfCreate());
        caseDto.setName(caseObject.getName());
        caseDto.setId(caseObject.getId());
        return caseDto;
    }
}
