package app.ladderproject.core.service.exception;

import app.ladderproject.core.utility.view.Message;
import app.ladderproject.core.domain.dto.Notification;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * @author nima
 * @apiNote this class used for create Exceptions And Notification
 * @since 1.0.1
 * @param <R> is the type Of Exception that you can return From Method
 */
public interface ApplicationException<R extends ServiceException> {

    /**
     * @param exceptionKey this key that fetches from Resource properties
     * @return the Object Of Exception Class that you can throw it
     */
    R createApplicationException(String exceptionKey);

    /**
     * @param message is the Object of message that you must impl it {@link Message}
     * @return the Object Of Exception Class that you can throw it
     */
    R createApplicationException(Message message);

    /**
     * @param message is the Object of message that you must impl it {@link Message}
     * @return the Object Of notification Class
     * @apiNote this method used for create notification
     */
    Notification createApplicationWarning(Message message);

    /**
     * @param messages is the Object of message that you must impl it {@link Message}
     * @return the Object Of notification Class
     * @apiNote this method used for create notification
     */
    List<Notification> createApplicationWarning(Message[] messages);

    /**
     * @param exceptionKey this key that fetches from Resource properties
     * @param httpStatus   is the status of Http Response {@link org.springframework.http.HttpStatus}
     * @return the Object Of Exception Class that you can throw it
     */
    R createApplicationException(String exceptionKey, HttpStatus httpStatus);

    /**
     * @param message    is the Object of message that you must impl it {@link Message}
     * @param httpStatus is the status of Http Response {@link org.springframework.http.HttpStatus}
     * @return the Object Of Exception Class that you can throw it
     */
    R createApplicationException(Message message, HttpStatus httpStatus);

}
