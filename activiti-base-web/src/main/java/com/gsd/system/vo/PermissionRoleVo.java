package com.gsd.system.vo;

import lombok.Data;

import java.util.List;

@Data
public class PermissionRoleVo {
    private List<TreeVo> list;
    private Long roleId;
}
