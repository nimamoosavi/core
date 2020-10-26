package ir.webold.framework.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.webold.framework.domain.dto.BaseDTO;
import ir.webold.framework.domain.viewmodel.PermissionVMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ir.webold.framework.config.general.GeneralStatic.*;

@Component
public class OauthService {

    @Value("${spring.application.name}")
    private String microserviceName;

    @Value("${oauth.microservice.address}")
    private String oauthUrl;

    @Value("${basic.microservice.authentication}")
    private String basicPass;

    @Value("${oauth.microservice.enable}")
    private boolean oauthEnable;


    private final ObjectMapper objectMapper;

    private final RestTemplate restTemplate;

    private final Map<String, PermissionVMS> permissions = new HashMap<>();

    @Autowired
    public OauthService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public void setAllMicroservicePermissions(ConfigurableApplicationContext context) {
        try {
            if (!microserviceName.equals(MS_USER_MANAGEMENT) && oauthEnable) {
                Map<String, String> map = new HashMap<>();
                map.put(APPLICATION_NAME, microserviceName);
                map.put(BASIC_AUTHENTICATION, basicPass);
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setAll(map);
                HttpEntity<Object> httpEntity = new HttpEntity<>(null, httpHeaders);
                String body = restTemplate.exchange(oauthUrl, HttpMethod.GET, httpEntity, String.class).getBody();
                BaseDTO<List<PermissionVMS>> objects = objectMapper.readValue(body, new TypeReference<BaseDTO<List<PermissionVMS>>>() {
                });
                setPermissionsToMap(objects.getData());
            }
        } catch (Exception e) {
            context.close();
        }
    }

    public Map<String, PermissionVMS> getPermissions() {
        return permissions;
    }

    public void setPermissionsToMap(List<PermissionVMS> permissionVMSList) {
        if (permissionVMSList != null) {
            for (PermissionVMS permissionVMS : permissionVMSList) {
                permissions.put(permissionVMS.getUrl(), permissionVMS);
            }
        }
    }


}
