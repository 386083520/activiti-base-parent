package com.gsd.security.permission.Vo;

import com.gsd.system.entity.SysPermission;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MenuVo implements Serializable {
    private List<SysPermission> menuList;
    private List<String> authList;
    private List<SysPermission> routerList;
    private String token;
    private Long userId;
}
