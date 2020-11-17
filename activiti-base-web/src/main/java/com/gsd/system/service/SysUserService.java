package com.gsd.system.service;

import com.gsd.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author sdguan
 * @since 2020-11-04
 */
public interface SysUserService extends IService<SysUser> {
    SysUser getUserByUserName(String username);
}
