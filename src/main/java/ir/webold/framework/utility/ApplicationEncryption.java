package ir.webold.framework.utility;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ir.webold.framework.domain.dto.BaseDTO;
import ir.webold.framework.enums.exception.ExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import static ir.webold.framework.service.GeneralService.successCustomResponse;

@Component
public class ApplicationEncryption {
    private final ApplicationException applicationException;

    @Autowired
    public ApplicationEncryption(ApplicationException applicationException) {
        this.applicationException = applicationException;
    }

    @Value("${jwtSecretKey}")
    private static String secretAppKeys;


    public BaseDTO<String> generateJwt(Map<String, Object> put, String secretKey, @NotNull Long exp) {
        Claims claims = Jwts.claims();
        setExpireTime(exp, claims);
        claims.putAll(put);
        String compact = Jwts.builder().addClaims(claims).signWith(SignatureAlgorithm.HS512, secretKey).compact();
        return successCustomResponse(compact);
    }

    public BaseDTO<String> generateJwt(Map<String, Object> put, @NotNull Long exp) {
        Claims claims = Jwts.claims();
        setExpireTime(exp, claims);
        claims.putAll(put);
        String compact = Jwts.builder().addClaims(claims).signWith(SignatureAlgorithm.HS512, secretAppKeys).compact();
        return successCustomResponse(compact);
    }

    public BaseDTO<Boolean> validTokenWithoutCheckExpireTime(String jwt, String secretKey) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt);
            return successCustomResponse(true);
        } catch (Exception e) {
            return successCustomResponse(false);
        }
    }

    public BaseDTO<Boolean> validTokenWithCheckExpireTime(String jwt, String secretKey) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
            return checkExpireTime(claims);
        } catch (Exception e) {
            return successCustomResponse(false);
        }
    }

    public BaseDTO<Boolean> validTokenWithoutCheckExpireTime(String jwt) {
        try {
            Jwts.parser().setSigningKey(secretAppKeys).parseClaimsJws(jwt);
            return successCustomResponse(true);
        } catch (Exception e) {
            return successCustomResponse(false);
        }
    }

    public BaseDTO<Boolean> validTokenWithCheckExpireTime(String jwt) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretAppKeys).parseClaimsJws(jwt).getBody();
            return checkExpireTime(claims);
        } catch (Exception e) {
            return successCustomResponse(false);
        }
    }

    public BaseDTO<Claims> getJwtBodyWithoutCheckExpireTime(String jwt) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretAppKeys).parseClaimsJws(jwt).getBody();
            return successCustomResponse(claims);
        } catch (Exception e) {
            throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_INVALID, HttpStatus.BAD_REQUEST);
        }
    }

    public BaseDTO<Claims> getJwtBodyWithCheckExpireTime(String jwt) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretAppKeys).parseClaimsJws(jwt).getBody();
            if (Boolean.TRUE.equals(checkExpireTime(claims).getData()))
                return successCustomResponse(claims);
            else
                throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_EXPIRED, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_INVALID, HttpStatus.BAD_REQUEST);
        }
    }

    public BaseDTO<Claims> getJwtBodyWithoutCheckExpireTime(String jwt, String secretKey) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
            return successCustomResponse(claims);
        } catch (Exception e) {
            throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_INVALID, HttpStatus.BAD_REQUEST);
        }
    }

    public BaseDTO<Claims> getJwtBodyWithCheckExpireTime(String jwt, String secretKey) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
            if (Boolean.TRUE.equals(checkExpireTime(claims).getData()))
                return successCustomResponse(claims);
            else
                throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_EXPIRED, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_INVALID, HttpStatus.BAD_REQUEST);
        }
    }

    public BaseDTO<Object> getJwtParamWithoutCheckExpireTime(String jwt, String paramName) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretAppKeys).parseClaimsJws(jwt).getBody();
            return successCustomResponse(claims.get(paramName));
        } catch (Exception e) {
            throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_INVALID, HttpStatus.BAD_REQUEST);
        }
    }

    public BaseDTO<Object> getJwtParamWithCheckExpireTime(String jwt) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretAppKeys).parseClaimsJws(jwt).getBody();
            if (Boolean.TRUE.equals(checkExpireTime(claims).getData()))
                return successCustomResponse(claims);
            else
                throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_EXPIRED, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_INVALID, HttpStatus.BAD_REQUEST);
        }
    }

    public BaseDTO<Object> getJwtParamWithoutCheckExpireTime(String jwt, String secretKey, String paramName) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
            return successCustomResponse(claims.get(paramName));
        } catch (Exception e) {
            throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_INVALID, HttpStatus.BAD_REQUEST);
        }
    }

    public BaseDTO<Object> getJwtParamWithCheckExpireTime(String jwt, String secretKey) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
            if (Boolean.TRUE.equals(checkExpireTime(claims).getData()))
                return successCustomResponse(claims);
            else
                throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_EXPIRED, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw applicationException.createApplicationException(ExceptionEnum.JWT_TOKEN_INVALID, HttpStatus.BAD_REQUEST);
        }
    }


    private void setExpireTime(Long expireTime, Claims claims) {
        claims.setExpiration(new Date(System.currentTimeMillis() + expireTime));
    }

    private BaseDTO<Boolean> checkExpireTime(Claims claims) {
        if (Boolean.TRUE.equals(claims.getExpiration().after(new Date(System.currentTimeMillis()))))
            return successCustomResponse(true);
        else
            return successCustomResponse(false);
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
