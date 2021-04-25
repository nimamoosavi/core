package com.webold.core.packages.audit.view;

import com.webold.core.packages.audit.view.file.FileReq;
import com.webold.core.packages.audit.view.kafka.KafkaReq;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuditLocation {
    KafkaReq kafka;
    FileReq file;
}
