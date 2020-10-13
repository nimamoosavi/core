package ir.webold.framework.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEvent;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    private String eventType;
    private String body;

}
