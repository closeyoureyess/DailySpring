package com.example.myProject.config;

import com.example.myProject.dto.CaseDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootStrapServers;

    @Bean
    private ConsumerFactory<String, CaseDto> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        JsonDeserializer<CaseDto> deserializer = new JsonDeserializer<>(CaseDto.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

   /* @Bean
    public ConsumerFactory<String, CaseDto> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(CaseDto.class));
    }*/

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, CaseDto>> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, CaseDto> consFactory = new ConcurrentKafkaListenerContainerFactory<>();
        /*consFactory.setConsumerFactory(consumerFactory());*/
        consFactory.setConsumerFactory(consumerConfigs());
        return consFactory;
    }
}
