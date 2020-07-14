package ir.webold.framework.domain.viewmodel.propertyvalue.response;

import ir.webold.framework.domain.viewmodel.BaseReqVM;
import ir.webold.framework.domain.viewmodel.BaseResVM;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PropertyValueResVM extends BaseResVM<Long> {

    private String farsiTittle;
    private String englishTittle;
    private String code;
    private String parentCode;
    private String description;
}


