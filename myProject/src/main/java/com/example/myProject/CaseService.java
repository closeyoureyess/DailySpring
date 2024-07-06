package com.example.myProject;

import com.example.myProject.dto.CaseDto;
import com.example.myProject.mapper.CaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

@Service
public class CaseService {
    private static Map<Integer, CaseDto> cases = new HashMap<>();
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
        /*cases.put(caseObject.getId(), new CaseDto(caseObject.getName(), caseObject.getDateOfCreate()));*/
        return caseObject;
    }

    public boolean searchInformation(Integer id) {
        if (caseRepository.existsById(id)) {
            return true;
        }
        return false;
    }

    public CaseDto searchName(Integer id) {
        if (caseRepository.findById(id).isPresent()) {
            return caseMapper.convertCaseToDto(caseRepository.findById(id).get());
        }
        return null;
    }

    public CaseDto changeCase(Integer id, CaseDto caseObject) {
        /*HashMap<Integer, CaseDto> forReplace = new HashMap<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        caseObject.setDateOfCreate(localDateTime.toLocalTime().toString());
        for (int key : cases.keySet()) {
            if (id == key) {
                forReplace.put(id, new CaseDto(caseObject.getName(), caseObject.getDateOfCreate()));
                break;
            }
        }
        for (Map.Entry<Integer, CaseDto> enumeration : cases.entrySet()) {
            if (id != enumeration.getKey()) {
                forReplace.put(enumeration.getKey(), new CaseDto(enumeration.getValue().getName(), enumeration.getValue().getDateOfCreate()));
            }
        }
        cases.clear();
        cases.putAll(forReplace);*/

        if(caseRepository.findById(id).isPresent()){
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

    public List<CaseDto> getCases() {
        return caseMapper.convertCaseToListDto(caseRepository.findAll());
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
