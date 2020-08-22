package ir.webold.framework.filter;

import ir.webold.framework.utility.ApplicationRequest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

import static ir.webold.framework.config.general.GeneralStatic.RRN;


@WebFilter(urlPatterns = {"/*"})
public class AppFilter implements Filter {

    ApplicationRequest applicationRequest;

    @Autowired
    public AppFilter(ApplicationRequest applicationRequest) {
        this.applicationRequest = applicationRequest;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(req);
        if (applicationRequest.getHeader(RRN) == null)
            mutableRequest.putHeader(RRN, UUID.randomUUID().toString());
        filterChain.doFilter(mutableRequest, servletResponse);
    }
}
