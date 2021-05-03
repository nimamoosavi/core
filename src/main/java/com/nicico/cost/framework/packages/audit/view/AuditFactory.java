package com.nicico.cost.framework.packages.audit.view;

import lombok.Data;
import lombok.Getter;

import java.util.List;

public class AuditFactory {
    private AuditHeader header;
    private List<Object> input;
    private Object result;

    private AuditFactory() {
    }

    public static AuditFactory builder() {
        return new AuditFactory();
    }

    public AuditFactory ok(Object o) {
        this.result = o;
        return this;
    }

    public AuditFactory inputs(List<Object> inputs) {
        this.input = inputs;
        return this;
    }

    public AuditFactory header(AuditHeader header) {
        this.header = header;
        return this;
    }

    public AuditFactory exception(AuditException exception) {
        this.result = exception;
        return this;
    }

    public AuditVM build() {
        AuditVM auditVM = new AuditVM();
        auditVM.setData(this.result);
        auditVM.setHeader(this.header);
        auditVM.setInput(this.input);
        return auditVM;
    }

    @Data
    public class AuditVM {
        private AuditHeader header;
        private List<Object> input;
        private Object data;
    }

    @Getter
    public enum AuditType {
        BEFORE,
        AFTER_RETURNING,
        AFTER_TROWING,
        AROUND
    }

    @Getter
    public enum Status {
        OK,
        ERROR,
        WARN
    }
}
