package com.webold.framework.config.general;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GeneralStatic {

    public static final String AUTHORIZATION = "Authorization";
    public static final String CORRELATION_ID = "correlationId";
    public static final String CLIENT_VERSION = "client-version";
    public static String datePersianPatternWithDetail;
    public static String datePersianPattern;
    public static String applicationName;
    public static String instanceId;
    public static int connectionTimeOut;
    public static int readTimeOut;

    @Value("${connection.timeout:5000}")
    public void setConnectionTimeOut(int connectionTimeOut) {
        GeneralStatic.connectionTimeOut = connectionTimeOut;
    }

    @Value("${read.timeout:5000}")
    public void setReadTimeOut(int readTimeOut) {
        GeneralStatic.readTimeOut = readTimeOut;
    }

    @Value("${core.datePersianPatternWithDetail:yyyy/MM/dd hh:mm}")
    public void setDatePersianPatternWithDetail(String datePersianPatternWithDetail) {
        GeneralStatic.datePersianPatternWithDetail = datePersianPatternWithDetail;
    }

    @Value("${core.datePersianPattern:yyyy/MM/dd}")
    public void setDatePersianPattern(String datePersianPattern) {
        GeneralStatic.datePersianPattern = datePersianPattern;
    }

    @Value("${application.instanceId}")
    public void setInstanceId(String instanceId) {
        GeneralStatic.instanceId = instanceId;
    }

}
