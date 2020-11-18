package com.gsd.system.service;

import com.gsd.system.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author sdguan
 * @since 2020-11-04
 */
public interface SysPermissionService extends IService<SysPermission> {
    List<SysPermission> getPermissionListByUserId(Long userId);
    List<SysPermission> getPermissionListByRoleId(Long roleId);
}
