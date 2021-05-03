package com.nicico.cost.framework.config.general;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GeneralStatic {

    private static String refreshToken;
    public static String basic;
    public static String datePersianPatternWithDetail;
    public static String datePersianPattern;
    private static String basicAuthentication;
    public static String applicationName;
    private static String roles;
    private static String userName;
    private static String userId;
    public static final String  AUTHORIZATION = "Authorization";
    public static final String APP_KEY = "appKey";
    public static final String CORRELATION_ID = "correlationId";
    private static String requestId;
    private static String swaggerTittle;
    private static String swaggerDescription;
    private static String swaggerLicence;
    private static String swaggerVersion;
    public static String instanceId;


    @Value("${core.refreshToken:refreshToken}")
    public void setRefreshToken(String refreshToken) {
        GeneralStatic.refreshToken = refreshToken;
    }
    @Value("${core.basic:Basic}")
    public void setBasic(String basic) {
        GeneralStatic.basic = basic;
    }

    @Value("${core.datePersianPatternWithDetail:yyyy/MM/dd hh:mm}")
    public void setDatePersianPatternWithDetail(String datePersianPatternWithDetail) {
        GeneralStatic.datePersianPatternWithDetail = datePersianPatternWithDetail;
    }

    @Value("${core.datePersianPattern:yyyy/MM/dd}")
    public void setDatePersianPattern(String datePersianPattern) {
        GeneralStatic.datePersianPattern = datePersianPattern;
    }
    @Value("${core.basic:Basic}")
    public void setBasicAuthentication(String basicAuthentication) {
        GeneralStatic.basicAuthentication = basicAuthentication;
    }

    @Value("${application.name}")
    public void setApplicationName(String applicationName) {
        GeneralStatic.applicationName = applicationName;
    }

    @Value("${core.roles:roles}")
    public void setRoles(String roles) {
        GeneralStatic.roles = roles;
    }

    @Value("${core.username}")
    public void setUserName(String userName) {
        GeneralStatic.userName = userName;
    }

    @Value("${core.userId:userId}")
    public void setUserId(String userId) {
        GeneralStatic.userId = userId;
    }


    @Value("${core.requestId:requestId}")
    public void setRequestId(String requestId) {
        GeneralStatic.requestId = requestId;
    }

    @Value("${application.instanceId}")
    public void setInstanceId(String instanceId) {
        GeneralStatic.instanceId = instanceId;
    }
}
