package com.gsd.security.detailservice;

import com.gsd.system.entity.SysPermission;
import com.gsd.system.entity.SysUser;
import com.gsd.system.service.SysPermissionService;
import com.gsd.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("customerUserDetailService")
public class CustomerUserDetailsService implements UserDetailsService{
    @Autowired
    private SysUserService userService;
    //这里需要注入PasswordEncoder，不然会报错的
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SysPermissionService sysPermissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userService.getUserByUserName(username);
        if(sysUser == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        // 查询用户所有的权限
        List<SysPermission> permissionList = sysPermissionService.getPermissionListByUserId(sysUser.getId());
        List<String> collect = permissionList.stream().filter(item -> item != null).map(item -> item.getCode()).collect(Collectors.toList());
        String[] strings = collect.toArray(new String[collect.size()]);
        List<GrantedAuthority> authorityList =
                AuthorityUtils.createAuthorityList(strings);
        sysUser.setAuthorities(authorityList);
        sysUser.setPermissionList(permissionList);
        return sysUser;
    }
}
