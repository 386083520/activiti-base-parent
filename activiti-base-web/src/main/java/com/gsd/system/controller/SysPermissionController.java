package com.gsd.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gsd.result.CodeStatus;
import com.gsd.result.ResultUtils;
import com.gsd.result.ResultVo;
import com.gsd.system.entity.SysPermission;
import com.gsd.system.entity.SysUser;
import com.gsd.system.service.SysPermissionService;
import com.gsd.system.service.SysUserService;
import com.gsd.system.vo.PerVo;
import com.gsd.system.vo.TreeVo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author sdguan
 * @since 2020-11-04
 */
@RestController
@RequestMapping("/system/sys-permission")
public class SysPermissionController {
    @Autowired
    private SysUserService userService;

    @Autowired
    private SysPermissionService permissionService;

    @ApiOperation(value = "查询菜单列表")
    @RequestMapping(value = "/getMenuList", method = RequestMethod.GET)
    public ResultVo getMenuList() {
        QueryWrapper<SysPermission> query = new QueryWrapper<>();
        query.lambda().orderByAsc(SysPermission::getOrderNum);
        List<SysPermission> list = permissionService.list(query);
        List<SysPermission> menuList = null;
        if(!list.isEmpty()){
            menuList = makeTree(list, 0L);
        }
        return ResultUtils.success("成功", CodeStatus.SUCCESS_CODE, menuList);
    }

    @ApiOperation(value = "新增权限")
    @RequestMapping(value = "/addPermission", method = RequestMethod.POST)
    public ResultVo addPermission(@RequestBody SysPermission permission) {
        boolean b = permissionService.save(permission);
        if(b) {
            return ResultUtils.success("新增成功");
        } else {
            return ResultUtils.error("新增失败");
        }
    }

    @ApiOperation(value = "角色分配权限树查询")
    @RequestMapping(value = "/permissionTree", method = RequestMethod.POST)
    public ResultVo permissionTree(@RequestBody PerVo perVo) {
        Long userId = perVo.getUserId();
        List<SysPermission> permissionList =  null;
        // 查询当前用户的所有权限
        SysUser user = userService.getById(userId);
        if (StringUtils.isNotEmpty(user.getIsAdmin()) && user.getIsAdmin().equals("1")) {
            permissionList = permissionService.list();
        }else {
            permissionList = permissionService.getPermissionListByUserId(userId);
        }
        // 查询角色原来的数据为选中
        List<SysPermission> sysPermissionsByRole = permissionService.getPermissionListByRoleId(perVo.getRoleId());
        List<TreeVo> listTree = new ArrayList<>();
        if(permissionList != null) {
            for (int i = 0; i < permissionList.size(); i++) {
                if (permissionList.get(i) != null) {
                    TreeVo treeVo = new TreeVo();
                    treeVo.setId(permissionList.get(i).getId());
                    treeVo.setPid(permissionList.get(i).getParentId());
                    treeVo.setName(permissionList.get(i).getLabel());
                    if(sysPermissionsByRole.size() > 0) {
                        for (int j = 0; j < sysPermissionsByRole.size(); j++) {
                            if (permissionList.get(i).getId().equals(sysPermissionsByRole.get(j).getId())) {
                                treeVo.setChecked(true);
                                break;
                            }
                        }
                    }
                    listTree.add(treeVo);
                }
            }
        }
        return ResultUtils.success("查询成功", listTree);
    }

    @RequestMapping(value = "getMenuById",method = RequestMethod.POST)
    public ResultVo getMenuById(@RequestBody SysPermission permission){
        SysPermission menu = permissionService.getById(permission.getId());
        return ResultUtils.success("成功",menu);
    }

    @RequestMapping(value = "/editSave",method = RequestMethod.POST)
    public ResultVo editSave(@RequestBody SysPermission permission){
        permission.setCreateTime(new Date());
        boolean res = permissionService.updateById(permission);
        if(res){
            return ResultUtils.success("更新成功");
        } else {
            return ResultUtils.error("更新失败");
        }
    }

    @RequestMapping(value = "/deletePermission",method = RequestMethod.POST) public ResultVo deleteEntity(@RequestBody SysPermission permission){
        boolean b = permissionService.removeById(permission.getId());
        if(b){
            return ResultUtils.success("删除成功!");
        }else{
            return ResultUtils.error("删除失败!");
        }
    }

    @RequestMapping(value = "/getParentTree",method = RequestMethod.POST)
    public ResultVo getParentTree(){
        QueryWrapper<SysPermission> query = new QueryWrapper<>();
        query.lambda().eq(SysPermission::getType,"0").or().eq(SysPermission::getType,"1");
        List<SysPermission> list = permissionService.list(query);
        List<TreeVo> listTree = new ArrayList<>();
        TreeVo parentTree = new TreeVo();
        parentTree.setId(0L);
        parentTree.setPid(-1L);
        parentTree.setName("顶级菜单");
        parentTree.setOpen(true);
        parentTree.setChecked(false);
        listTree.add(parentTree);
        if(list.size() > 0){
            for(SysPermission p : list){
                if(p != null){
                    TreeVo tree = new TreeVo();
                    tree.setId(p.getId());
                    tree.setPid(p.getParentId());
                    tree.setName(p.getLabel());
                    tree.setOpen(true);
                    tree.setChecked(false);
                    listTree.add(tree);
                }
            }
        }
        return ResultUtils.success("成功",listTree);
    }

    private static List<SysPermission> makeTree(List<SysPermission> menuList, Long pId) {
        List<SysPermission> children = menuList.stream().filter(x -> x.getParentId() == pId).collect(Collectors.toList());
        List<SysPermission> successor = menuList.stream().filter(x -> x.getParentId() != pId).collect(Collectors.toList());
        if (children.size() > 0) {
            children.forEach(x -> {
                if(successor.size() > 0){
                    makeTree(successor, x.getId()).forEach(
                            y -> x.getChildren().add(y)
                    );
                }
            });
        }
        return children;
    }

}

