package com.gsd.security.permission.Vo;

import com.gsd.system.entity.SysPermission;

import java.util.List;
import java.util.stream.Collectors;

public class MakeMenuTree {
    public static List<SysPermission> makeTree(List<SysPermission> menuList, Long pid){
        List<SysPermission> children = menuList.stream().filter(x -> x.getParentId() == pid).collect(Collectors.toList());
        List<SysPermission> successor = menuList.stream().filter(x -> x.getParentId() != pid).collect(Collectors.toList());
        if(children.size() > 0) {
            children.forEach(x -> {
                if(successor.size() > 0) {
                    makeTree(successor, x.getId()).forEach(y -> {
                        x.getChildren().add(y);
                    });
                }
            });
        }
        return children;

    }
}
