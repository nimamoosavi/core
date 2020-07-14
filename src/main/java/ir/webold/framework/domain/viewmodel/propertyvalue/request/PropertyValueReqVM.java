package ir.webold.framework.domain.viewmodel.propertyvalue.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PropertyValueReqVM {
    @NotNull
    @NotBlank
    private String farsiTittle;
    @NotNull
    @NotBlank
    private String englishTittle;
    @NotNull
    @NotBlank
    private String code;
    private String parentCode;
    private String description;
}
