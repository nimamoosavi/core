package ir.webold.framework.service;

import ir.webold.framework.domain.viewmodel.PermissionVMS;
import ir.webold.framework.utility.ApplicationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static ir.webold.framework.config.general.GeneralStatic.APPLICATION_NAME;
import static ir.webold.framework.config.general.GeneralStatic.BASIC_AUTHENTICATION;

@Component
public class AuthenticationService {

    @Value("${spring.application.name}")
    private String microserviceName;

    @Value("${oauth.microservice.address}")
    private String oauthUrl;

    @Value("${basic.microservice.authentication}")
    private String basicPass;


    private final ApplicationRequest applicationRequest;

    private final RestTemplate restTemplate;

    private final Map<String, PermissionVMS> permissions = new HashMap<>();

    @Autowired
    public AuthenticationService(ApplicationRequest applicationRequest,RestTemplate restTemplate) {
        this.applicationRequest = applicationRequest;
        this.restTemplate = restTemplate;
    }

    public void getAllMicroservicePermissions() {
        Map<String, String> map = new HashMap<>();
        map.put(APPLICATION_NAME, microserviceName);
        map.put(BASIC_AUTHENTICATION, basicPass);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(map);
        HttpEntity<Object> httpEntity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange(oauthUrl, HttpMethod.GET, httpEntity, String.class);

        System.out.println("nima");
    }


}
