package com.nicico.cost.framework.service.exception;

import com.nicico.cost.framework.domain.dto.Notification;
import com.nicico.cost.framework.utility.request.Message;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * @author nima
 * @apiNote this class used for create Exceptions And Notification
 */
public interface ApplicationException {

    /**
     * @param exceptionKey this key that fetch from Resource properties
     * @return the Object Of Exception Class that you can throw it
     */
    ServiceException createApplicationException(String exceptionKey);

    /**
     * @param message is the Object of message that you must impl it {@link com.nicico.cost.framework.utility.request.Message}
     * @return the Object Of Exception Class that you can throw it
     */
    ServiceException createApplicationException(Message message);

    /**
     * @param message is the Object of message that you must impl it {@link com.nicico.cost.framework.utility.request.Message}
     * @return the Object Of notification Class
     * @apiNote this method used for create notification
     */
    Notification createApplicationWarning(Message message);

    /**
     * @param messages is the Object of message that you must impl it {@link com.nicico.cost.framework.utility.request.Message}
     * @return the Object Of notification Class
     * @apiNote this method used for create notification
     */
    List<Notification> createApplicationWarning(Message[] messages);

    /**
     * @param exceptionKey this key that fetch from Resource properties
     * @param httpStatus   is the status of Http Response {@link org.springframework.http.HttpStatus}
     * @return the Object Of Exception Class that you can throw it
     */
    ServiceException createApplicationException(String exceptionKey, HttpStatus httpStatus);

    /**
     * @param message    is the Object of message that you must impl it {@link com.nicico.cost.framework.utility.request.Message}
     * @param httpStatus is the status of Http Response {@link org.springframework.http.HttpStatus}
     * @return the Object Of Exception Class that you can throw it
     */
    ServiceException createApplicationException(Message message, HttpStatus httpStatus);

}
