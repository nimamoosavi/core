package ir.webold.framework.aop;

import io.jsonwebtoken.Claims;
import ir.webold.framework.domain.viewmodel.PermissionVMS;
import ir.webold.framework.enums.exception.ExceptionEnum;
import ir.webold.framework.exception.ServiceException;
import ir.webold.framework.service.OauthService;
import ir.webold.framework.utility.ApplicationEncryption;
import ir.webold.framework.utility.ApplicationException;
import ir.webold.framework.utility.ApplicationRedis;
import ir.webold.framework.utility.ApplicationRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static ir.webold.framework.config.general.GeneralStatic.*;

@Aspect
@Component
public class AuthorizeService {

    @Value("${oauth.microservice.enbale}")
    private boolean oauthEnable;

    private final ApplicationRequest applicationRequest;
    private final OauthService oauthService;
    private final ApplicationEncryption applicationEncryption;
    private final ApplicationRedis applicationRedis;
    private final ApplicationException applicationException;
    private final AuditService auditService;

    @Autowired
    public AuthorizeService(ApplicationRequest applicationRequest, OauthService oauthService, ApplicationEncryption applicationEncryption, ApplicationRedis applicationRedis, ApplicationException applicationException, AuditService auditService) {
        this.applicationRequest = applicationRequest;
        this.oauthService = oauthService;
        this.applicationEncryption = applicationEncryption;
        this.applicationRedis = applicationRedis;
        this.applicationException = applicationException;
        this.auditService = auditService;
    }


    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restController() {
    }

    @Pointcut("within(@ir.webold.framework.anotations.Oauth *)")
    public void oauth() {
    }


    @Before("restController() || oauth()")
    public void authorize(JoinPoint joinPoint) {
        if (oauthEnable) {
            boolean access = false;
            String jwtToken = applicationRequest.getHeader(AUTHORIZATION);
            applicationRequest.removeSession(USERNAME);
            applicationRequest.removeSession(USERID);
            List<String> roles = null;
            if (jwtToken != null) {
                Claims claims;
                try {
                    claims = applicationEncryption.getJwtBody(jwtToken).getData();
                } catch (Exception e) {
                    auditService.logAfterThrowing(joinPoint, e);
                    throw e;
                }
                Object checkExpireStorage = applicationRedis.fetch(claims.getId(), false).getData();
                if (checkExpireStorage != null) {
                    ServiceException e = this.applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_INVALID, HttpStatus.NOT_ACCEPTABLE);
                    auditService.logAfterThrowing(joinPoint, e);
                    throw e;
                }
                if (claims.get(ROLES) != null)
                    roles = (List<String>) claims.get(ROLES);
                applicationRequest.addSession(USERID, Long.valueOf(claims.getSubject()));
                applicationRequest.addSession(USERNAME, claims.get(USERNAME, String.class));
            }
            Map<String, PermissionVMS> permissions = oauthService.getPermissions();
            String requestURI = applicationRequest.getRequestURI();
            PermissionVMS permissionVMS = permissions.get(requestURI);
            if (permissionVMS != null && permissionVMS.getRolesList() != null) {
                if (roles == null) {
                    ServiceException e = this.applicationException.createApplicationException(ExceptionEnum.ACCESS_DENIED, HttpStatus.NOT_ACCEPTABLE);
                    auditService.logAfterThrowing(joinPoint, e);
                    throw e;
                }
                List<String> rolesList = permissionVMS.getRolesList();
                for (String s : rolesList) {
                    if (roles.contains(s)) {
                        access = true;
                        break;
                    }
                }
                if (!access) {
                    ServiceException e = this.applicationException.createApplicationException(ExceptionEnum.ACCESS_DENIED, HttpStatus.NOT_ACCEPTABLE);
                    auditService.logAfterThrowing(joinPoint, e);
                    throw e;
                }
            }
        }
    }
}
