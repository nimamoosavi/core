package com.webold.core.utility;

import com.webold.core.service.GeneralService;
import com.webold.core.domain.dto.BaseDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class ApplicationKafka {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final List<String> kafkaMessages = new CopyOnWriteArrayList<>();

    public ApplicationKafka(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    @KafkaListener(topics = "${kafka.audit.topic}", groupId = "${spring.kafka.consumer.group-id}", autoStartup = "${kafka.listener.enabled}")
    public void listen(ConsumerRecord<String, String> record) {
        kafkaMessages.add(record.value());
    }

    public BaseDTO<List<String>> getMessage() {
        return GeneralService.successCustomListResponse(kafkaMessages);
    }

    public BaseDTO<Boolean> deleteMessages() {
        kafkaMessages.clear();
        return GeneralService.successCustomResponse(true);
    }
}
