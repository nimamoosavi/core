package ir.webold.framework.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEvent;

@Data
public class EventDTO extends ApplicationEvent {
    private String eventType;
    private String body;

    public EventDTO(Object source, String eventType, String body) {
        super(source);
        this.eventType = eventType;
        this.body = body;
    }
}
