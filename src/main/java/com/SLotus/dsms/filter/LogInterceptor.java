package com.SLotus.dsms.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description: 打印日志拦截器
 *
 * @author SLotus
 * @date 2023-03-08
 */
@Slf4j
@WebFilter(urlPatterns = "/*", filterName = "logFilter")
@Order(1)
public class LogInterceptor implements Filter {
    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws javax.servlet.ServletException {
        log.info("LogInterceptor init");
    }

    //请求打印日志
    @Override
    public void doFilter(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, javax.servlet.FilterChain chain) throws javax.servlet.ServletException, java.io.IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String requestBody = req.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
        log.info("请求地址：{}，请求方式：{},请求体:{}", req.getRequestURL(), req.getMethod(), requestBody);
        chain.doFilter(request, response);
    }
}
