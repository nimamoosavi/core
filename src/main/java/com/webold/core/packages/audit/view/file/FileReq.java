package com.webold.core.packages.audit.view.file;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class FileReq {
    Type type;

    @Getter
    public enum Type {
        JSON,
        LOG4J
    }
}
