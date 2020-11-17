package com.gsd.security.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gsd.result.CodeStatus;
import com.gsd.result.ResultUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("customizeAuthenticationEntryPoint")
public class CustomizeAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("text/json;charset=utf-8");
        ServletOutputStream outputStream =
                httpServletResponse.getOutputStream();
        String res = JSON.toJSONString(ResultUtils.error("用户未登录", CodeStatus.NO_LOGIN), SerializerFeature.DisableCircularReferenceDetect);
        outputStream.write(res.getBytes("utf-8"));
        outputStream.flush();
        outputStream.close();
    }
}
