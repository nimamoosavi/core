package ir.webold.framework.utility;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ir.webold.framework.domain.dto.BaseDTO;
import ir.webold.framework.enums.ExceptionEnum;
import ir.webold.framework.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Map;

import static ir.webold.framework.service.GeneralService.successCustomResponse;

@Component
public class ApplicationEncryption {
    @Autowired
    ApplicationException applicationException;
    @Autowired
    ApplicationResource applicationResource;

    @Value("${jwtSecretKey}")
    static String secretAppKeys;


    public BaseDTO<String> generateJwt(Map<String, String> put, String secretKey) {
        Claims claims = Jwts.claims();
        claims.putAll(put);
        String compact = Jwts.builder().addClaims(claims).signWith(SignatureAlgorithm.HS512, secretKey).compact();
        return successCustomResponse(compact);
    }

    public BaseDTO<String> generateJwt(Map<String, String> put) {
        Claims claims = Jwts.claims();
        claims.putAll(put);
        String compact = Jwts.builder().addClaims(claims).signWith(SignatureAlgorithm.HS512, secretAppKeys).compact();
        return successCustomResponse(compact);
    }

    public BaseDTO<Boolean> validToken(String jwt, String secretKey) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt);
            return successCustomResponse(true);
        } catch (Exception e) {
            return successCustomResponse(false);
        }
    }

    public BaseDTO<Boolean> validToken(String jwt) {
        try {
            Jwts.parser().setSigningKey(secretAppKeys).parseClaimsJws(jwt);
            return successCustomResponse(true);
        } catch (Exception e) {
            return successCustomResponse(false);
        }
    }

    public BaseDTO<Claims> getJwtClaims(String jwt) {
        Claims claims = Jwts.parser().setSigningKey(secretAppKeys).parseClaimsJws(jwt).getBody();
        return successCustomResponse(claims);
    }

    public BaseDTO<Claims> getJwtClaims(String jwt, String secretKey) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
        return successCustomResponse(claims);
    }

    public BaseDTO<Object> getJwtParam(String jwt, String paramName) {
        Claims claims = Jwts.parser().setSigningKey(secretAppKeys).parseClaimsJws(jwt).getBody();
        return successCustomResponse(claims.get(paramName));
    }

    public BaseDTO<Object> getJwtParam(String jwt, String secretKey, String paramName) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
        return successCustomResponse(claims.get(paramName));
    }

    public BaseDTO<String> hash(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            String hashResult = Base64.getEncoder().encodeToString(hash);
            return successCustomResponse(hashResult);
        } catch (Exception e) {
            throw applicationException.createApplicationException(ExceptionEnum.INTERNALSERVER, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
