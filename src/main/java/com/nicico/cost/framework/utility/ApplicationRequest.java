package com.nicico.cost.framework.utility;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.Cookie;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface ApplicationRequest {
    String getClientIp();

    Map<String, String> getHeaders();

    String getHeader(String name);

    Boolean isHeader(String name);

    String getMethod();

    String getUrl();

    String getProtocol();

    String getHost();

    String getRequestURI();

    String getRequestDomain();

    Integer getServerPort();

    String getParam();

    String getParam(String param);

    String getContentType();

    List<Cookie> getCookies();

    Cookie getCookie(String name);

    Boolean isCookie(String name);

    String getSessionId();

    Timestamp getSessionLastAccessTime();

    Boolean isSessionNew();

    Timestamp getSessionCreateTime();

    String changeUserSession();

    void deactivateSession();

    Object getSession(String name);

    void addSession(String name, Object o);

    void removeSession(String name);

    <U> ResponseEntity<U> httpRequest(String domain, HttpMethod httpMethod, Map<String, String> headers, Object body, Class<U> targetClass);
}
