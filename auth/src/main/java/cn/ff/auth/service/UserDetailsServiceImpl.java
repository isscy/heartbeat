package cn.ff.auth.service;

import cn.ff.auth.entity.UserDetailsImpl;
import cn.ff.common.entity.SysRole;
import cn.ff.common.entity.SysUser;
import cn.ff.common.mapper.SysRoleMapper;
import cn.ff.common.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Deprecated
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    // todo sys_user 变成用户表 sys_account 变成后台账户表 使用不同的 UserDetailsService 来登陆
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserMapper.getByUserName(username);
        if (user == null)
            throw new UsernameNotFoundException("用户名找不到！");
        List<SysRole> roleCodes = sysRoleMapper.getOneUserRoles(user.getId());
        user.setRoles(roleCodes);
        return new UserDetailsImpl(user);

        // TODO 登陆错误 不打印堆栈

    }
}
