package com.webold.core.filter;

import com.webold.core.config.general.GeneralConfig;
import com.webold.core.config.general.GeneralStatic;
import com.webold.core.utility.ApplicationRequest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebFilter(urlPatterns = {"/*"})
public class AppFilter implements Filter {

    @Autowired
    public ApplicationRequest applicationRequest;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(req);
        MutableHttpServletResponse mutableResponse = new MutableHttpServletResponse(res);
        String rrn = applicationRequest.getHeader(GeneralStatic.CORRELATION_ID);
        if (rrn == null) {
            rrn = UUID.randomUUID().toString();
            mutableRequest.putHeader(GeneralStatic.CORRELATION_ID, rrn);
        }
        mutableResponse.putHeader(GeneralStatic.CORRELATION_ID, rrn);
        filterChain.doFilter(mutableRequest, mutableResponse);
    }
}
