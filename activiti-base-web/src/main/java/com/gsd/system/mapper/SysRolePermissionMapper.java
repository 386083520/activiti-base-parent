package com.gsd.system.mapper;

import com.gsd.system.entity.SysRolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色权限表 Mapper 接口
 * </p>
 *
 * @author sdguan
 * @since 2020-11-04
 */
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {
    boolean saveRolePermission(@Param("roleId") Long roleId, @Param("permissionIds") List<Long> permissionIds);
}
