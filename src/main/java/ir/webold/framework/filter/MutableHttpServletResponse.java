package ir.webold.framework.filter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.util.HashMap;
import java.util.Map;

public class MutableHttpServletResponse extends HttpServletResponseWrapper {

    private final Map<String, String> customHeaders;

    public MutableHttpServletResponse(HttpServletResponse response) {
        super(response);
        this.customHeaders = new HashMap<>();
    }

    public void putHeader(String name, String value) {
        this.customHeaders.put(name, value);
    }
}
