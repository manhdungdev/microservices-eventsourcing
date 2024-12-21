package com.fullstack.commonservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaService {
    private final KafkaTemplate<String,String> kafkaTemplate;

    /**
     *
     * @param topic
     * @param message
     */
    public void sendMessage(String topic, String message){
        kafkaTemplate.send(topic, message);
        log.info("Message send to topic: " + topic);
    }
}