package ir.webold.framework.aop;

import io.jsonwebtoken.Claims;
import ir.webold.framework.enums.exception.ExceptionEnum;
import ir.webold.framework.service.OauthService;
import ir.webold.framework.utility.ApplicationEncryption;
import ir.webold.framework.utility.ApplicationException;
import ir.webold.framework.utility.ApplicationRedis;
import ir.webold.framework.utility.ApplicationRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

import static ir.webold.framework.config.general.GeneralStatic.*;

@Aspect
@Component
public class AuthorizeService {

    @Value("${oauth.microservice.enbale}")
    private boolean oauthEnable;

    @Value("${spring.application.name}")
    private String microserviceName;

    private final ApplicationRequest applicationRequest;
    private final OauthService oauthService;
    private final ApplicationEncryption applicationEncryption;
    private final ApplicationRedis applicationRedis;
    private final ApplicationException applicationException;

    @Autowired
    public AuthorizeService(ApplicationRequest applicationRequest, OauthService oauthService, ApplicationEncryption applicationEncryption, ApplicationRedis applicationRedis, ApplicationException applicationException) {
        this.applicationRequest = applicationRequest;
        this.oauthService = oauthService;
        this.applicationEncryption = applicationEncryption;
        this.applicationRedis = applicationRedis;
        this.applicationException = applicationException;
    }


    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restController() {
    }


    @Before("restController()")
    public void logBefore() {
        if (oauthEnable) {
            String jwtToken = applicationRequest.getHeader(AUTHORIZATION);
            applicationRequest.removeSession(USERNAME);
            applicationRequest.removeSession(USERID);
            List<String> roles = null;
            if (jwtToken != null) {
                Object checkExpireStorage = applicationRedis.fetch(jwtToken, false).getData();
                if (checkExpireStorage != null)
                    throw applicationException.createApplicationException(ExceptionEnum.ACCESS_DENIED, HttpStatus.NOT_ACCEPTABLE);
                Claims claims = applicationEncryption.getJwtBody(jwtToken).getData();
                if (claims.get(ROLES) != null)
                    roles = (List<String>) claims.get(ROLES);
                applicationRequest.addSession(USERID, Long.valueOf(claims.getSubject()));
                applicationRequest.addSession(USERNAME, claims.get(USERNAME, String.class));
            }
        }
    }

}
