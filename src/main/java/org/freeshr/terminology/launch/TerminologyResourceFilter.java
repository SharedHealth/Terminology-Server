package org.freeshr.terminology.launch;


import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TerminologyResourceFilter implements Filter {
    protected final Log logger = LogFactory.getLog(getClass());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("Initializing Terminology Resource filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpSession session = request.getSession(false);
            if (session != null) {
                logger.debug("Setting session timeout to 10 seconds...");
                System.out.println("Setting session timeout to 10 seconds...");
                session.setMaxInactiveInterval(10);
            }

        }
    }

    @Override
    public void destroy() {
        logger.debug("Destroying Terminology Resource filter");
    }
}