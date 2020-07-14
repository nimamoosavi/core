package ir.webold.framework.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PageDTO<T> {
    @ApiModelProperty(value = "${PageDTO.totalElement.note}")
    private Long totalElement;
    @ApiModelProperty(value = "${PageDTO.totalPages.note}")
    private Integer totalPages;
    @ApiModelProperty(value = "${PageDTO.pageSize.note}")
    private Integer pageSize;
    private T object;
}
