package com.gsd.system.service;

import com.gsd.system.entity.SysRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;
import org.hibernate.validator.constraints.EAN;

import java.util.List;

/**
 * <p>
 * 角色权限表 服务类
 * </p>
 *
 * @author sdguan
 * @since 2020-11-04
 */
public interface SysRolePermissionService extends IService<SysRolePermission> {
    void saveAssignRole(Long roleId, List<Long> permissionIds);
}
