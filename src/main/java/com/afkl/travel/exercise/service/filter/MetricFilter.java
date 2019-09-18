package com.afkl.travel.exercise.service.filter;

import com.afkl.travel.exercise.service.MetricService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
@Order(1)
public class MetricFilter implements Filter {

    @Autowired
    private MetricService metricService;

    private Logger logger = LoggerFactory.getLogger(MetricFilter.class);

    @Override
    public void init(FilterConfig config) {
        metricService = (MetricService) WebApplicationContextUtils
                .getRequiredWebApplicationContext(config.getServletContext())
                .getBean("metricService");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws java.io.IOException, ServletException {

        final Long start = System.currentTimeMillis();
        HttpServletRequest httpRequest = ((HttpServletRequest) request);
        chain.doFilter(request, response);
        logger.info(UUID.randomUUID().toString() + " request: " + ((HttpServletRequest) request).getMethod()+ ((HttpServletRequest) request).getRequestURI());
        int status = ((HttpServletResponse) response).getStatus();
        metricService.statistics(status, System.currentTimeMillis()-start );





    }
}