package cn.ff.auth.service;

import cn.ff.common.entity.SysUser;
import cn.ff.common.mapper.SysRoleMapper;
import cn.ff.common.mapper.SysUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseUserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String var1) throws UsernameNotFoundException {
        SysUser baseUser = getUser(var1);
        List<String> roleCodes = sysRoleMapper.getOneUserRoleCodes(baseUser.getId());
        List<GrantedAuthority> authorities = convertToAuthorities(baseUser, roleCodes);
        // 返回带有用户权限信息的User
        org.springframework.security.core.userdetails.User user =  new org.springframework.security.core.userdetails.User(baseUser.getUserName(),
                baseUser.getPassword(), true, true, true, true, authorities);
        return user;
    }

    protected abstract SysUser getUser(String var1) ;


    private List<GrantedAuthority> convertToAuthorities(SysUser baseUser, List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(e -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(e);
            authorities.add(authority);
        });
        return authorities;
    }

}
