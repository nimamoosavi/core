package ir.webold.framework.filter;

import ir.webold.framework.utility.ApplicationRequest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static ir.webold.framework.config.general.GeneralStatic.RRN;


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
        String rrn = applicationRequest.getHeader(RRN);
        if (rrn == null) {
            rrn = UUID.randomUUID().toString();
            mutableRequest.putHeader(RRN, rrn);
        }
        mutableResponse.putHeader(RRN, rrn);
        filterChain.doFilter(mutableRequest, mutableResponse);
    }
}
