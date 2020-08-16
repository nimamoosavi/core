package ir.webold.framework.filter;

import ir.webold.framework.filter.MutableHttpServletResponse;
import ir.webold.framework.utility.ApplicationRequest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static ir.webold.framework.config.general.GeneralStatic.AUTHORIZATION;
import static ir.webold.framework.config.general.GeneralStatic.RRN;


@WebFilter(urlPatterns = {"/*"})
public class AppFilter implements Filter {
    @Autowired
    ApplicationRequest applicationRequest;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //Request Filter
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(req);
        if (applicationRequest.getHeader(RRN)==null)
            mutableRequest.putHeader(RRN, UUID.randomUUID().toString());
        // Response Filter
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        MutableHttpServletResponse mutableResponse = new MutableHttpServletResponse(response);
        mutableResponse.putHeader(RRN, applicationRequest.getHeader(RRN));
        mutableResponse.putHeader(AUTHORIZATION, applicationRequest.getHeader(AUTHORIZATION));
        filterChain.doFilter(mutableRequest, mutableResponse);
    }
}
