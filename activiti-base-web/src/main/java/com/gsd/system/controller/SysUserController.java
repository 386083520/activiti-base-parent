package com.gsd.system.controller;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.gsd.system.entity.SysUser;
import com.gsd.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apiguardian.api.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author sdguan
 * @since 2020-11-04
 */
@RestController
@RequestMapping("/system/sys-user")
@Api(value = "用户管理")
public class SysUserController {
    public static final String SESSION_KEY = "IMAGE_CODE";
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @GetMapping("/findUsers")
    @ApiOperation(value = "查询所有用户信息")
    public List<SysUser> findUsers() {
        return sysUserService.list();
    }
    @RequestMapping("/image")
    public void imageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store,no-cache");
        response.setContentType("image/jpeg");
        String code = defaultKaptcha.createText();
        request.getSession().setAttribute(SESSION_KEY, code);
        BufferedImage image = defaultKaptcha.createImage(code);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        if(outputStream != null) {
            outputStream.close();
        }
    }
}

