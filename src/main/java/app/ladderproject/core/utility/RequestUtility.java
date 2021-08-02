package app.ladderproject.core.utility;


import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.Cookie;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author nima
 * @apiNote this class used for get all data from request
 */
public interface RequestUtility {
    /**
     * @return the requestIp
     */
    String getClientIp();

    /**
     * @return headers of Request
     */
    Map<String, String> getHeaders();

    /**
     * @param name is the header key for get value
     * @return the value of header
     */
    String getHeader(String name);

    /**
     * @param name is the header key for get value
     * @return true Or false if the header is existed or Not
     */
    Boolean isHeader(String name);

    /**
     * @return the method name of Request
     */
    String getMethod();

    /**
     * @return the Path Of url that called
     */
    String getUrl();

    /**
     * @return the protocol Type of request url
     */
    String getProtocol();

    /**
     * @return the name of Host of Service
     */
    String getHost();

    /**
     * @return the Request Uri that Called
     */
    String getRequestURI();

    /**
     * @return the Request Domain
     */
    String getRequestDomain();

    /**
     * @return the Server Port that Called
     */
    Integer getServerPort();

    /**
     * @return All Request Param
     */
    String getParam();

    /**
     * @param param is the Request Param Name in Url
     * @return the Value Of Request Param
     */
    String getParam(String param);

    /**
     * @return ContentType Of Request
     */
    String getContentType();

    /**
     * @return all Cookies From Request
     */
    List<Cookie> getCookies();

    /**
     * @param name the Cookie Key That Send
     * @return the Object Of Cookie
     */
    Cookie getCookie(String name);

    /**
     * @param name the Cookie Key That Send
     * @return the True Or False for Excited
     */
    Boolean isCookie(String name);

    /**
     * @return the SessionId Of Request
     */
    String getSessionId();

    /**
     * @return the last Access For SessionId In Request
     */
    Timestamp getSessionLastAccessTime();

    /**
     * @return the true Or false For sessionId is New Created Or Not
     */
    Boolean isSessionNew();

    /**
     * @return the Session Created time
     */
    Timestamp getSessionCreateTime();

    /**
     * @return the New random SessionId
     * @apiNote remove the Session Id of User
     */
    String changeUserSession();

    /**
     * @apiNote Remove the SessionId Of Request
     */
    void deactivateSession();

    /**
     * @param name is the param name in Session
     * @return the value Of param Name Object
     */
    Object getSession(String name);

    /**
     * @param name is the param name in Session
     * @param o    the Object you want to set in session
     */
    void addSession(String name, Object o);

    /**
     * @param name is the param name in Session
     * @apiNote this methode Used For Remove the session Object
     */
    void removeSession(String name);

    Map<String, String[]> getRequestParam();

    /**
     * @param domain      is the Url Of Request
     * @param httpMethod  is the Type Of Request Such as POST,GET,...
     * @param headers     all Headers In Request that you want to send
     * @param body        the Request Body
     * @param targetClass the Class That You want To cast Response In It
     * @param <U>         the Type Of Request that You want To Cast Response In It
     * @return the Object Of Response
     * @apiNote this methode Used For Called RestFull Another Server
     */
    <U> ResponseEntity<U> httpRequest(String domain, HttpMethod httpMethod, Map<String, String> headers, Object body, Class<U> targetClass);

    /**
     * @param domain     is the Url Of Request
     * @param httpMethod is the Type Of Request Such as POST,GET,...
     * @param headers    all Headers In Request that you want to send
     * @param body       the Request Body
     * @return the Object Of Response
     * @apiNote this methode Used For Called RestFull Another Server
     */
    ResponseEntity<Object> httpRequest(String domain, HttpMethod httpMethod, Map<String, String> headers, Object body);

    /**
     * @param domain      is the Url Of Request
     * @param httpMethod  is the Type Of Request Such as POST,GET,...
     * @param headers     all Headers In Request that you want to send
     * @param body        the Request Body
     * @param targetClass the Class That You want To cast Response In It
     * @param <U>         the Type Of Request that You want To Cast Response In It
     * @param conTimeOut  is the connectionTime For Request
     * @param readTimeOut is the ReadTime Out for Connection
     * @return the Object Of Response
     * @apiNote this methode Used For Called RestFull Another Server
     */
    <U> ResponseEntity<U> httpRequest(String domain, HttpMethod httpMethod, Map<String, String> headers, Object body, Class<U> targetClass, int conTimeOut, int readTimeOut);

    /**
     * @param domain      is the Url Of Request
     * @param httpMethod  is the Type Of Request Such as POST,GET,...
     * @param headers     all Headers In Request that you want to send
     * @param body        the Request Body
     * @param conTimeOut  is the connectionTime For Request
     * @param readTimeOut is the ReadTime Out for Connection
     * @return the Object Of Response
     * @apiNote this methode Used For Called RestFull Another Server
     */
    ResponseEntity<Object> httpRequest(String domain, HttpMethod httpMethod, Map<String, String> headers, Object body, int conTimeOut, int readTimeOut);


}
