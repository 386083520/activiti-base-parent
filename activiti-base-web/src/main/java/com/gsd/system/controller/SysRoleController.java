package com.gsd.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gsd.result.ResultUtils;
import com.gsd.result.ResultVo;
import com.gsd.system.entity.SysRole;
import com.gsd.system.service.SysRoleService;
import com.gsd.system.vo.RoleVo;
import com.gsd.vo.ParamVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author sdguan
 * @since 2020-11-04
 */
@RestController
@RequestMapping("/system/sys-role")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public ResultVo addRole(@RequestBody SysRole role) {
        boolean b = sysRoleService.save(role);
        if(b) {
            return ResultUtils.success("新增成功");
        }else {
            return ResultUtils.error("新增失败");
        }
    }

    @RequestMapping(value = "/getRoleById", method = RequestMethod.POST)
    public ResultVo getRoleById(@RequestBody SysRole sysRole) {
        SysRole sysRole1 = sysRoleService.getById(sysRole.getId());
        return ResultUtils.success("查询成功", sysRole1);
    }

    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    public ResultVo updateRole(@RequestBody SysRole sysRole) {
        boolean b = sysRoleService.updateById(sysRole);
        if(b) {
            return ResultUtils.success("修改成功");
        }else  {
            return ResultUtils.error("修改失败");
        }
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public ResultVo deleteById(@RequestBody SysRole sysRole) {
        boolean b = sysRoleService.removeById(sysRole);
        if(b) {
            return ResultUtils.success("删除成功");
        }else {
            return ResultUtils.error("删除失败");
        }
    }

    @RequestMapping(value = "/getRoleList", method = RequestMethod.POST)
    public ResultVo getRoleList(@RequestBody RoleVo roleVo) {
        QueryWrapper<SysRole> query = new QueryWrapper<>();
        if(!StringUtils.isBlank(roleVo.getTitle())) {
            query.lambda().like(SysRole::getName, roleVo.getTitle());
        }
        IPage<SysRole> page = new Page<>();
        page.setSize(roleVo.getPageSize());
        page.setCurrent(roleVo.getCurrentPage());
        IPage<SysRole> roleList = sysRoleService.page(page, query);
        return ResultUtils.success("查询成功", roleList);
    }

}

