package com.synonyms.config;

import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.regex.Pattern;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleRequestFilter implements Filter {

    //private final ConfigHelper configHelper;
    public static final String DISALLOWED_CHARACTERS = "'<>#*^!~`}{[]%\\";
    public static final Pattern PATTERN_INVALID_FIELD_VALUE = Pattern.compile("['<>#*^!~`}{\\[\\]%]+");
    public static final Set<String> WHITELIST_FIELDS = Set.of("password", "oldPassword", "newPassword", "uniqueIds",
            "file", "filename", "url", "offerId", "billParams.cn", "billerName", "configValue", "txnMessage");

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT,PATCH,OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "x-requested-with, authorization, cache-control, content-type, accept");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache,no-store,max-age=0,must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        response.setHeader("X-Content-Type-Options", "nosniff");
        response.setHeader("X-Frame-Options", "DENY");
        response.setHeader("X-XSS-Protection", "1; mode=block");
        response.setHeader("referrer-policy", "same-origin");
        response.setHeader("Strict-Transport-Security", "max-age=31536000 ; includeSubDomains");

        String sessionid = request.getSession() == null ? "" : request.getSession().getId();

        if (response.containsHeader("set-cookie")) {
            response.setHeader("set-cookie", "JSESSIONID=" + sessionid + ";Path=/; Secure; HttpOnly");
        }
        MDC.put("sessionId", sessionid);
        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
