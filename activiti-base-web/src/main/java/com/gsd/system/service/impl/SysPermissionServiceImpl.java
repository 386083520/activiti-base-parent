package com.gsd.system.service.impl;

import com.gsd.system.entity.SysPermission;
import com.gsd.system.mapper.SysPermissionMapper;
import com.gsd.system.service.SysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author sdguan
 * @since 2020-11-04
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Override
    public List<SysPermission> getPermissionListByUserId(Long userId) {
        return this.baseMapper.getPermissionListByUserId(userId);
    }

    @Override
    public List<SysPermission> getPermissionListByRoleId(Long roleId) {
        return this.baseMapper.getPermissionListByRoleId(roleId);
    }
}
