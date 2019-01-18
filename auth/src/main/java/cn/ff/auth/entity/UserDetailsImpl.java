package cn.ff.auth.entity;

import cn.ff.common.constant.UserConstant;
import cn.ff.common.entity.SysUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private String username;
    private String password;
    private boolean lock;
    private List<GrantedAuthority> authorities;

    public UserDetailsImpl(){}

    public UserDetailsImpl(SysUser user){
        this.username = user.getUserName();
        this.password = user.getPassword();
        if (StringUtils.isBlank(user.getStatus()))
            lock = false; // 已锁住
        lock = UserConstant.STATUS_1_NORMAL.equals(user.getStatus()) ;

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(e -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(e.getCode());
            authorities.add(authority);
        });
        this.authorities = authorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return lock;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
