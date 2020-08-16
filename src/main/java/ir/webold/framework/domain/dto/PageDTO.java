package ir.webold.framework.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PageDTO<T> {
    private Long totalElement;
    private Integer totalPages;
    private Integer pageSize;
    private T object;
}
