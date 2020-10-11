package ir.webold.framework.utility;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ir.webold.framework.domain.dto.BaseDTO;
import ir.webold.framework.domain.viewmodel.JwtObjReqVM;
import ir.webold.framework.enums.exception.ExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;

import static ir.webold.framework.service.GeneralService.successCustomResponse;

@Component
public class ApplicationEncryption {

    private final ApplicationException applicationException;

    @Autowired
    public ApplicationEncryption(ApplicationException applicationException) {
        this.applicationException = applicationException;
    }

    @Value("${jwtSecretKey}")
    private String secretAppKeys;


    public BaseDTO<String> generateJwt(JwtObjReqVM jwtObjReqVM, String secretKey) {
        Claims claims = Jwts.claims();
        claims.setExpiration(jwtObjReqVM.getExp());
        claims.setId(jwtObjReqVM.getJti());
        claims.setSubject(jwtObjReqVM.getSubject());
        claims.setAudience(jwtObjReqVM.getAud());
        claims.setIssuedAt(jwtObjReqVM.getIat());
        claims.setNotBefore(jwtObjReqVM.getNbf());
        claims.setId(jwtObjReqVM.getJti());
        claims.setIssuer(jwtObjReqVM.getIss());
        claims.putAll(jwtObjReqVM.getCustoms());
        String compact = Jwts.builder().addClaims(claims).signWith(SignatureAlgorithm.HS512, secretKey).compact();
        return successCustomResponse(compact);
    }

    public BaseDTO<String> generateJwt(JwtObjReqVM jwtObjReqVM) {
        Claims claims = Jwts.claims();
        claims.setExpiration(jwtObjReqVM.getExp());
        claims.setId(jwtObjReqVM.getJti());
        claims.setSubject(jwtObjReqVM.getSubject());
        claims.setAudience(jwtObjReqVM.getAud());
        claims.setIssuedAt(jwtObjReqVM.getIat());
        claims.setNotBefore(jwtObjReqVM.getNbf());
        claims.setId(jwtObjReqVM.getJti());
        claims.setIssuer(jwtObjReqVM.getIss());
        claims.putAll(jwtObjReqVM.getCustoms());
        String compact = Jwts.builder().addClaims(claims).signWith(SignatureAlgorithm.HS512, secretAppKeys).compact();
        return successCustomResponse(compact);
    }

    public BaseDTO<Boolean> isTokenWithoutCheckExpireTime(String jwt, String secretKey) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt);
            return successCustomResponse(true);
        } catch (Exception e) {
            return successCustomResponse(false);
        }
    }

    public BaseDTO<Boolean> isValid(String jwt, String secretKey) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
            return successCustomResponse(true);
        } catch (Exception e) {
            return successCustomResponse(false);
        }
    }

    public BaseDTO<Boolean> isValidWithoutCheckExpireTime(String jwt) {
        try {
            Jwts.parser().setSigningKey(secretAppKeys).parseClaimsJws(jwt);
            return successCustomResponse(true);
        } catch (Exception e) {
            return successCustomResponse(false);
        }
    }

    public BaseDTO<Boolean> isValid(String jwt) {
        try {
            Jwts.parser().setSigningKey(secretAppKeys).parseClaimsJws(jwt).getBody();
            return successCustomResponse(true);
        }catch (ExpiredJwtException e){
            throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_EXPIRED, HttpStatus.BAD_REQUEST);
        }  catch (Exception e) {
            throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_INVALID, HttpStatus.BAD_REQUEST);
        }
    }

    public BaseDTO<Claims> getJwtBodyWithoutCheckExpireTime(String jwt) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretAppKeys).parseClaimsJws(jwt).getBody();
            return successCustomResponse(claims);
        }catch (ExpiredJwtException e){
            return successCustomResponse(e.getClaims());
        }  catch (Exception e) {
            throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_INVALID, HttpStatus.BAD_REQUEST);
        }
    }

    public BaseDTO<Claims> getJwtBody(String jwt) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretAppKeys).parseClaimsJws(jwt).getBody();
            return successCustomResponse(claims);
        }catch (ExpiredJwtException e){
            throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_EXPIRED, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_INVALID, HttpStatus.BAD_REQUEST);
        }
    }

    public BaseDTO<Claims> getJwtBodyWithoutCheckExpireTime(String jwt, String secretKey) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
            return successCustomResponse(claims);
        }catch (ExpiredJwtException e){
            return successCustomResponse(e.getClaims());
        }
        catch (Exception e) {
            throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_INVALID, HttpStatus.BAD_REQUEST);
        }
    }

    public BaseDTO<Claims> getJwtBody(String jwt, String secretKey) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
            return successCustomResponse(claims);
        }catch (ExpiredJwtException e){
            throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_EXPIRED, HttpStatus.BAD_REQUEST);
        }  catch (Exception e) {
            throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_INVALID, HttpStatus.BAD_REQUEST);
        }
    }

    public <T> BaseDTO<T> getJwtParam(String jwt, String paramName, Class<T> tClass) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretAppKeys).parseClaimsJws(jwt).getBody();
            return successCustomResponse(claims.get(paramName, tClass));
        }catch (ExpiredJwtException e){
            throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_EXPIRED, HttpStatus.BAD_REQUEST);
        }  catch (Exception e) {
            throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_INVALID, HttpStatus.BAD_REQUEST);
        }
    }


    public <T> BaseDTO<T> getJwtParam(Claims claims, String paramName, Class<T> tClass) {
        try {
            return successCustomResponse(claims.get(paramName, tClass));
        } catch (Exception e) {
            throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_INVALID, HttpStatus.BAD_REQUEST);
        }
    }

    public BaseDTO<Object> getJwtParam(String jwt) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretAppKeys).parseClaimsJws(jwt).getBody();
            return successCustomResponse(claims);
        } catch (Exception e) {
            throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_INVALID, HttpStatus.BAD_REQUEST);
        }
    }

    public <T> BaseDTO<T> getJwtParamWithoutCheckExpireTime(String jwt, String secretKey, String paramName, Class<T> tClass) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
            return successCustomResponse(claims.get(paramName, tClass));
        }catch (ExpiredJwtException e){
            return successCustomResponse(e.getClaims().get(paramName, tClass));
        }  catch (Exception e) {
            throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_INVALID, HttpStatus.BAD_REQUEST);
        }
    }

    public <T> BaseDTO<T> getJwtParam(String jwt, String secretKey, String paramName, Class<T> tClass) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
            return successCustomResponse(claims.get(paramName, tClass));
        } catch (Exception e) {
            throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_INVALID, HttpStatus.BAD_REQUEST);
        }
    }

    private BaseDTO<Boolean> checkExpireTime(Claims claims) {
        if (Boolean.TRUE.equals(claims.getExpiration().after(new Date(System.currentTimeMillis()))))
            return successCustomResponse(true);
        else
            throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_EXPIRED, HttpStatus.BAD_REQUEST);
    }

    public BaseDTO<String> hash(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            String hashResult = Base64.getEncoder().encodeToString(hash);
            return successCustomResponse(hashResult);
        } catch (Exception e) {
            throw applicationException.createApplicationException(ExceptionEnum.INTERNAL_SERVER, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
