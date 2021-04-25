package com.webold.core.config.general;

import org.springframework.beans.factory.annotation.Value;

public class GeneralStatic {




    public static String REFRESH_TOKEN = "";

    @Value("${core.refreshToken:refreshToken}")
    public static void setRefreshToken(String refreshToken) {
        REFRESH_TOKEN = refreshToken;
    }

    public static final String BASIC = "Basic ";
    public static final String DEFAULT_PERSIAN_DATE_PATTERN_WITH_TIME = "yyyy/MM/dd hh:mm";
    public static final String DEFAULT_PERSIAN_DATE_PATTERN = "yyyy/MM/dd";
    public static final String RABBIT_LOG_OUT_EXCHANGE = "global.logout.exchange";
    public static final String BASIC_AUTHENTICATION = "Basic";
    public static final String APPLICATION_NAME = "name";
    public static final String MS_USER_MANAGEMENT = "ms-user-management";
    public static final String ROLES = "roles";
    public static final String USERNAME = "username";
    public static final String USERID = "userId";
    public static final String AUTHORIZATION = "Authorization";
    public static final String APP_KEY = "appKey";
    public static final String CORRELATION_ID = "correlationId";
    public static final String REQUEST_ID = "requestId";

}
