package com.gsd.system.controller;


import com.gsd.result.ResultUtils;
import com.gsd.result.ResultVo;
import com.gsd.system.service.SysRolePermissionService;
import com.gsd.system.vo.PerVo;
import com.gsd.system.vo.PermissionRoleVo;
import com.gsd.system.vo.TreeVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色权限表 前端控制器
 * </p>
 *
 * @author sdguan
 * @since 2020-11-04
 */
@RestController
@RequestMapping("/system/sys-role-permission")
public class SysRolePermissionController {
    @Autowired
    private SysRolePermissionService rolePermissionService;

    @ApiOperation(value = "角色分配权限树查询")
    @RequestMapping(value = "/saveAssignRole", method = RequestMethod.POST)
    public ResultVo saveAssignRole(@RequestBody PermissionRoleVo permissionRoleVo) {
        if(permissionRoleVo != null && !permissionRoleVo.getList().isEmpty()){
            List<TreeVo> list = permissionRoleVo.getList();
            Long roleId = permissionRoleVo.getRoleId();
            List<Long> ids = list.stream().filter(item -> item != null).map(item -> item.getId()).collect(Collectors.toList());
            rolePermissionService.saveAssignRole(roleId, ids);
            return ResultUtils.success("分配权限成功");
        }else {
            return ResultUtils.error("分配权限失败");
        }
    }
}

