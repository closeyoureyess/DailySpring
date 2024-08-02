package com.example.myProject.services;

import com.example.myProject.dto.CaseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaListnerService {

    @Autowired
    private CaseService caseService;

    

    @KafkaListener(topics = "${kafka.topic}", groupId = "customProjectGroup")
    public void performKafkaMessage(CaseDto caseDto) {
        log.info("Пришла сущность " + caseDto.getDateOfCreate() + " " + caseDto.getName() + " " + caseDto.getId());

    }
}
