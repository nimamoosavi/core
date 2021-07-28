package com.webold.framework.aop;

import org.aspectj.lang.JoinPoint;

/**
 * @version 1.0.1
 * @author nima
 * @implNote this class Used For generate Warning After Response Send To Client
 * @since 1.0.1
 */
public interface WarningService {

    /**
     *
     * @param joinPoint the joinPoint of Aop
     * @param result the Object Of Result (BASE_DTO)
     * @apiNote this method used for controller and service layer or service to Return BaseDto {@link com.nicico.cost.framework.domain.dto}
     */
    void warnings(JoinPoint joinPoint, Object result);
}
