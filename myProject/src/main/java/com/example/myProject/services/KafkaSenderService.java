/*package com.example.myProject.services;

import com.example.myProject.dto.CaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaSenderService {

    @Value("${kafka.topic}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, CaseDto> kafkaTemplate;

    public KafkaSenderService(KafkaTemplate<String, CaseDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(CaseDto caseDto) {
        kafkaTemplate.send(topicName, caseDto);
    }
}*/
