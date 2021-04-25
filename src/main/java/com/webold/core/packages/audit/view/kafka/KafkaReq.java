package com.webold.core.packages.audit.view.kafka;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class KafkaReq {
    private String kafkaTopic;
    private Type kafkaType;

    @Getter
    private enum Type {
        JSON
    }
}
