package com.gsd.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gsd.system.entity.SysRolePermission;
import com.gsd.system.mapper.SysRolePermissionMapper;
import com.gsd.system.service.SysRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 角色权限表 服务实现类
 * </p>
 *
 * @author sdguan
 * @since 2020-11-04
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

    @Override
    @Transactional
    public void saveAssignRole(Long roleId, List<Long> permissionIds) {
        // 删除原来的全部权限
        QueryWrapper<SysRolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysRolePermission::getRoleId, roleId);
        this.baseMapper.delete(queryWrapper);
        // 保存新的权限
        this.baseMapper.saveRolePermission(roleId, permissionIds);
    }
}
