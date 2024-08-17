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
        if (caseDto.getDateOfCreate() != null) {
            caseLocalObject.setDateOfCreate(caseDto.getDateOfCreate());
        }
        caseLocalObject.setName(caseDto.getName());
        caseLocalObject.setId(caseDto.getId());
        return caseLocalObject;
    }

    public CaseDto convertCaseToDto(Case caseObject) {
        CaseDto caseDto = new CaseDto();
        if (caseObject.getDateOfCreate() != null) {
            caseDto.setDateOfCreate(caseObject.getDateOfCreate());
        }
        caseDto.setName(caseObject.getName());
        caseDto.setId(caseObject.getId());
        return caseDto;
    }

    public List<CaseDto> transferCaseToCaseDtoList(List<Case> caseList){
        List<CaseDto> caseDtoList = new LinkedList<>();
        for (int i = 0; i < caseList.size(); i++) {
            caseDtoList.add(new CaseDto(caseList.get(i).getId(), caseList.get(i).getName(),
                    caseList.get(i).getDateOfCreate()));
        }
        return caseDtoList;
    }
}
