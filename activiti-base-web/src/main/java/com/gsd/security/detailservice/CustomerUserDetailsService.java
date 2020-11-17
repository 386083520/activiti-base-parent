package com.gsd.security.detailservice;

import com.gsd.system.entity.SysUser;
import com.gsd.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("customerUserDetailService")
public class CustomerUserDetailsService implements UserDetailsService{
    @Autowired
    private SysUserService userService;
    //这里需要注入PasswordEncoder，不然会报错的
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userService.getUserByUserName(username);
        if(sysUser == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return sysUser;
    }
}
