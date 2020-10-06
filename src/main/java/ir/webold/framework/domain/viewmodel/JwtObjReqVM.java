package ir.webold.framework.domain.viewmodel;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
@Builder
public class JwtObjReqVM {
    private Map<String, Object> customs;
    private String jti;
    private Date iat;
    private Date exp;
    private String aud;
    private Date nbf;
    private String subject;
    private String iss;
}
