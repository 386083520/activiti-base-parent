package com.gsd.system.mapper;

import com.gsd.system.entity.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author sdguan
 * @since 2020-11-04
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    List<SysPermission> getPermissionListByUserId(@Param("userId") Long userId);
    List<SysPermission> getPermissionListByRoleId(@Param("roleId") Long roleId);
}
