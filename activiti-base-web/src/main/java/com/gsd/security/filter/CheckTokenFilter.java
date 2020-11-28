package com.gsd.security.filter;

import com.gsd.jwt.JwtUtils;
import com.gsd.security.detailservice.CustomerUserDetailsService;
import com.gsd.security.exception.TokenException;
import com.gsd.security.handler.LoginFailureHandler;
import com.gsd.security.exception.ImageCodeException;
import com.gsd.system.controller.SysUserController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("checkTokenFilter")
public class CheckTokenFilter extends OncePerRequestFilter{
    @Value("${gsd.loginUrl}")
    private String loginUrl;
    @Value("${gsd.imgUrl}")
    private String imgUrl;
    @Autowired
    private LoginFailureHandler loginFailureHandler;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if(uri.equals(loginUrl)) {
            try {
                validate(request);
            }catch (AuthenticationServiceException e) {
                loginFailureHandler.onAuthenticationFailure(request, response, e);
            }
        } else {
            String requestImgUrl = request.getRequestURI();
            if(!requestImgUrl.equals(imgUrl)) {
               try{
                   validateToken(request);
               }catch (AuthenticationException e) {
                   loginFailureHandler.onAuthenticationFailure(request, response, e);
               }
            }

        }
        filterChain.doFilter(request, response);
    }

    private void validateToken(HttpServletRequest request){
        String token = request.getHeader("token");
        String username = jwtUtils.getUsernameFromToken(token);
        if(StringUtils.isBlank(token) || StringUtils.isBlank(username)){
            throw new TokenException("token验证失败!");
        }
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);
        if(userDetails == null){
            throw new TokenException("token验证失败!");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void validate(HttpServletRequest request) {
        String code = request.getParameter("code");
        String sessionCode = (String)request.getSession().getAttribute(SysUserController.SESSION_KEY);
        if(StringUtils.isEmpty(code)) {
            throw new ImageCodeException("验证码不能为空");
        }
        if(!code.equalsIgnoreCase(sessionCode)) {
            throw new ImageCodeException("验证码输入错误");
        }
    }
}
