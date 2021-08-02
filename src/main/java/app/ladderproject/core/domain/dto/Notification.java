package app.ladderproject.core.domain.dto;


import app.ladderproject.core.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class Notification {
    private Status status;
    private Object notify;
}
