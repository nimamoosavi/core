package ir.webold.framework.enums.exception;

import lombok.Getter;


@Getter
public enum ExceptionEnum {
    SUCCESS,
    NOTFOUND,
    NOTFOUND_BY_ID,
    NOTFOUND_ALL_BY_ID,
    NOTFOUND_ROLE,
    NOTFOUND_USER,
    SERVER_DENY,
    NOT_DEVELOP,
    INTERNAL_SERVER,
    NOT_SAVE,
    NOT_UPDATE,
    NOT_DELETE,
    NOT_DELETE_BY_ID,
    NOT_DELETE_ALL,
    TOKEN_EXPIRE_TIME,
    ACCOUNT_ENABLE,
    ACCOUNT_EXPIRE,
    ACCOUNT_LOCKED,
    LOGIN_FAILED,
    MAXIMUM_LOGIN,
    ENVIRONMENT_NOT_FOUND,
    REFRESH_TOKEN_EXPIRE_TIME,
    DOUBLE_ROLE_ADDED,
    NOTFOUND_USER_INFORMATION,
    IPG_ORDER_TYPE_NOT_FOUND,
    IPG_REF_NUMBER_NOT_FOUND,
    IPG_REF_NUMBER_EXPIRED,
    OLD_PASSWORD_NOT_CORRECTED,
    JWT_TOKEN_INVALID,
    JWT_TOKEN_EXPIRED,
    NOT_FOUND_APPLICATION,
    NOT_FOUND_MICROSERVICE,
    NOT_FOUND_USER_APPLICATION
}
