package ir.webold.framework.domain.viewmodel;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PermissionVMS extends BaseResVM<Long> {
    String resource;
    String url;
    String httpMethod;
    Long microserviceId;
    List<String> rolesList;
}
