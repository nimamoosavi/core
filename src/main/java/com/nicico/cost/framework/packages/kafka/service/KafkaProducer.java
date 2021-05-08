package com.nicico.cost.framework.packages.kafka.service;

import com.nicico.cost.framework.domain.dto.BaseDTO;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

public interface KafkaProducer {

    ProducerFactory<String, Object> producerFactory(Map<String, Object> configs);

    void send(String topic, String o);

    BaseDTO<Boolean> sendSynchronous(String topic, String o);

    void send(String topic, String o, ProducerFactory<String, Object> config);

    BaseDTO<Boolean> sendSynchronous(String topic, String o, ProducerFactory<String, Object> config);
}
