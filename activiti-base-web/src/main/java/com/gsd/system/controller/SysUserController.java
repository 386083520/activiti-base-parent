package com.gsd.system.controller;


import com.gsd.system.entity.SysUser;
import com.gsd.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apiguardian.api.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/findUsers")
    @ApiOperation(value = "查询所有用户信息")
    public List<SysUser> findUsers() {
        return sysUserService.list();
    }
}

