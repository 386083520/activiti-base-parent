package com.gsd.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gsd.jwt.JwtUtils;
import com.gsd.result.ResultUtils;
import com.gsd.security.permission.Vo.MakeMenuTree;
import com.gsd.security.permission.Vo.MenuVo;
import com.gsd.system.entity.SysPermission;
import com.gsd.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component("loginSuccessHandler")
public class LoginSuccessHandler implements AuthenticationSuccessHandler{
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        SysUser sysUser = (SysUser) authentication.getPrincipal();
        String token = jwtUtils.generateToken(sysUser);
        MenuVo menuVo = new MenuVo();
        menuVo.setToken(token);
        menuVo.setUserId(sysUser.getId());
        List<SysPermission> permissionList = sysUser.getPermissionList();
        List<String> collect = permissionList.stream().filter(item -> item != null).map(item -> item.getCode()).collect(Collectors.toList());
        menuVo.setAuthList(collect);
        List<SysPermission> menuList = permissionList.stream().filter(item -> item != null && item.getType() != 2).collect(Collectors.toList());
        List<SysPermission> menuListTree = MakeMenuTree.makeTree(menuList, 0L);
        menuVo.setMenuList(menuListTree);
        List<SysPermission> routerList = permissionList.stream().filter(item -> item != null && item.getType() == 1).collect(Collectors.toList());
        menuVo.setRouterList(routerList);
        String res = JSONObject.toJSONString(ResultUtils.success("登陆成功",menuVo), SerializerFeature.DisableCircularReferenceDetect);
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream =
                httpServletResponse.getOutputStream();
        outputStream.write(res.getBytes("UTF-8"));
        outputStream.flush();
    }
}
