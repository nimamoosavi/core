package ir.webold.framework.service;

import ir.webold.framework.domain.viewmodel.PermissionVMS;
import ir.webold.framework.utility.ApplicationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AuthenticationService {

    @Value("${spring.application.name}")
    private String microserviceName;

    @Value("${oauth.microservice.address}")
    private String oauthUrl;



    private final ApplicationRequest applicationRequest;

    private final Map<String, PermissionVMS> permissions = new HashMap<>();

    @Autowired
    public AuthenticationService(ApplicationRequest applicationRequest) {
        this.applicationRequest = applicationRequest;
    }

    /*public void getAllMicroservicePermissions() {
        applicationRequest.httpRequest(oauthUrl, HttpMethod.GET,)

    }*/


}
