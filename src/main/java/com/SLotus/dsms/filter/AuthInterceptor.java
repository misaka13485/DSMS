package com.SLotus.dsms.filter;


import com.SLotus.dsms.service.ITUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;


/**
 * 登录与权限拦截器
 */
@Slf4j
@Order(2)
@WebFilter(urlPatterns = "/api/*", filterName = "authFilter")
public class AuthInterceptor implements Filter {
    @Autowired
    private ITUserInfoService userInfoService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("AuthInterceptor init");

    }

    //URI白名单
    private final String[] whiteList = {
            "/Swagger-resources",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/springfox-swagger-ui",
            "/t-user-info/login",
            "/t-user-info/register",
            "/t-user-info/verify"
    };

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //如果是登录请求，放行
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        if (Arrays.asList(whiteList).stream().anyMatch(uri::contains)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String userID = req.getHeader("userID");
        String token = req.getHeader("token");
        //如果Token不匹配，返回false
        if (!userInfoService.verifyToken(userID, token)) {
            res.setStatus(401);
            log.info("token验证不通过");
            return;
        }
        log.info("token验证通过");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
