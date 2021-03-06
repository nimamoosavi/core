package app.ladderproject.core.utility.impl;

import app.ladderproject.core.utility.RequestUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.*;

import static app.ladderproject.core.config.general.GeneralStatic.*;

@Component
@RequiredArgsConstructor
class RequestUtilityImpl implements RequestUtility {


    private final RestTemplate restTemplate;

    protected static final String[] IP_HEADER_CANDIDATES = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"};

    public String getClientIp() {
        HttpServletRequest request = requestContextHolder();
        for (String header : IP_HEADER_CANDIDATES) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip))
                return ip;
        }
        return null;
    }

    public Map<String, String> getHeaders() {
        HttpServletRequest request = requestContextHolder();
        Map<String, String> map = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    public String getHeader(String name) {
        HttpServletRequest request = requestContextHolder();
        return request.getHeader(name);
    }

    public Boolean isHeader(String name) {
        HttpServletRequest request = requestContextHolder();
        return request.getHeader(name) != null;
    }

    public String getMethod() {
        HttpServletRequest request = requestContextHolder();
        return request.getMethod();
    }

    public String getUrl() {
        HttpServletRequest request = requestContextHolder();
        return request.getRequestURL().toString();
    }

    public String getProtocol() {
        HttpServletRequest request = requestContextHolder();
        return request.getProtocol();
    }

    public String getHost() {
        HttpServletRequest request = requestContextHolder();
        return request.getRemoteHost();
    }

    public String getRequestURI() {
        HttpServletRequest request = requestContextHolder();
        return request.getRequestURI();
    }

    public String getRequestDomain() {
        HttpServletRequest request = requestContextHolder();
        return request.getScheme().concat("://").concat(request.getServerName()).concat(":").concat(request.getServerPort() + "");
    }

    public Integer getServerPort() {
        HttpServletRequest request = requestContextHolder();
        return request.getServerPort();
    }

    public String getParam() {
        HttpServletRequest request = requestContextHolder();
        return request.getQueryString();
    }

    public String getParam(String param) {
        HttpServletRequest request = requestContextHolder();
        return request.getParameter(param);
    }

    public String getContentType() {
        HttpServletRequest request = requestContextHolder();
        return request.getContentType();
    }

    public List<Cookie> getCookies() {
        HttpServletRequest request = requestContextHolder();
        return Arrays.asList(request.getCookies());
    }

    public Cookie getCookie(String name) {
        HttpServletRequest request = requestContextHolder();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name))
                return cookie;
        }
        return null;
    }

    public Boolean isCookie(String name) {
        HttpServletRequest request = requestContextHolder();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name))
                return true;
        }
        return false;
    }

    public String getSessionId() {
        HttpServletRequest request = requestContextHolder();
        return request.getSession().getId();
    }

    public Timestamp getSessionLastAccessTime() {
        HttpServletRequest request = requestContextHolder();
        return new Timestamp(request.getSession().getLastAccessedTime());
    }

    public Boolean isSessionNew() {
        HttpServletRequest request = requestContextHolder();
        return request.getSession().isNew();
    }

    public Timestamp getSessionCreateTime() {
        HttpServletRequest request = requestContextHolder();
        return new Timestamp(request.getSession().getCreationTime());
    }

    public String changeUserSession() {
        HttpServletRequest request = requestContextHolder();
        return request.changeSessionId();
    }

    public void deactivateSession() {
        HttpServletRequest request = requestContextHolder();
        request.getSession().invalidate();
    }

    public Object getSession(String name) {
        HttpServletRequest request = requestContextHolder();
        return request.getSession().getAttribute(name);
    }

    public void addSession(String name, Object o) {
        HttpServletRequest request = requestContextHolder();
        request.getSession().setAttribute(name, o);
    }

    public void removeSession(String name) {
        HttpServletRequest request = requestContextHolder();
        request.getSession().removeAttribute(name);
    }

    public Map<String, String[]> getRequestParam() {
        HttpServletRequest request = requestContextHolder();
        return request.getParameterMap();
    }

    public <U> ResponseEntity<U> httpRequest(String domain, HttpMethod httpMethod, Map<String, String> headers, Object body, Class<U> targetClass) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        if (headers != null)
            httpHeaders.setAll(headers);
        String authorization = getHeader(AUTHORIZATION);
        if (authorization != null)
            httpHeaders.set(AUTHORIZATION, authorization);
        String correlationId = getHeader(CORRELATION_ID);
        if (correlationId != null)
            httpHeaders.set(CORRELATION_ID, correlationId);
        String clientVersion = getHeader(CLIENT_VERSION);
        if (clientVersion != null)
            httpHeaders.set(CLIENT_VERSION, clientVersion);
        HttpEntity<Object> httpEntity = new HttpEntity<>(body, httpHeaders);
        return restTemplate.exchange(domain, httpMethod, httpEntity, targetClass);
    }

    private HttpServletRequest requestContextHolder() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    @Override
    public ResponseEntity<Object> httpRequest(String domain, HttpMethod httpMethod, Map<String, String> headers, Object body) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        if (headers != null)
            httpHeaders.setAll(headers);
        String authorization = getHeader(AUTHORIZATION);
        if (authorization != null)
            httpHeaders.set(AUTHORIZATION, authorization);
        String correlationId = getHeader(CORRELATION_ID);
        if (correlationId != null)
            httpHeaders.set(CORRELATION_ID, correlationId);
        String clientVersion = getHeader(CLIENT_VERSION);
        if (clientVersion != null)
            httpHeaders.set(CLIENT_VERSION, clientVersion);
        HttpEntity<Object> httpEntity = new HttpEntity<>(body, httpHeaders);
        return restTemplate.exchange(domain, httpMethod, httpEntity, Object.class);
    }

    @Override
    public <U> ResponseEntity<U> httpRequest(String domain, HttpMethod httpMethod, Map<String, String> headers, Object body, Class<U> targetClass, int conTimeOut, int readTimeOut) {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate customRestTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofMillis(conTimeOut))
                .setReadTimeout(Duration.ofMillis(readTimeOut))
                .build();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        if (headers != null)
            httpHeaders.setAll(headers);
        String authorization = getHeader(AUTHORIZATION);
        if (authorization != null)
            httpHeaders.set(AUTHORIZATION, authorization);
        String correlationId = getHeader(CORRELATION_ID);
        if (correlationId != null)
            httpHeaders.set(CORRELATION_ID, correlationId);
        String clientVersion = getHeader(CLIENT_VERSION);
        if (clientVersion != null)
            httpHeaders.set(CLIENT_VERSION, clientVersion);
        HttpEntity<Object> httpEntity = new HttpEntity<>(body, httpHeaders);
        return customRestTemplate.exchange(domain, httpMethod, httpEntity, targetClass);
    }

    @Override
    public ResponseEntity<Object> httpRequest(String domain, HttpMethod httpMethod, Map<String, String> headers, Object body, int conTimeOut, int readTimeOut) {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate customRestTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofMillis(conTimeOut))
                .setReadTimeout(Duration.ofMillis(readTimeOut))
                .build();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        if (headers != null)
            httpHeaders.setAll(headers);
        String authorization = getHeader(AUTHORIZATION);
        if (authorization != null)
            httpHeaders.set(AUTHORIZATION, authorization);
        String correlationId = getHeader(CORRELATION_ID);
        if (correlationId != null)
            httpHeaders.set(CORRELATION_ID, correlationId);
        String clientVersion = getHeader(CLIENT_VERSION);
        if (clientVersion != null)
            httpHeaders.set(CLIENT_VERSION, clientVersion);
        HttpEntity<Object> httpEntity = new HttpEntity<>(body, httpHeaders);
        return customRestTemplate.exchange(domain, httpMethod, httpEntity, Object.class);
    }
}
