package cn.ff.auth.provider;

import cn.ff.auth.helper.DefaultLoginAuthenticationFilter;
import cn.ff.auth.token.DefaultAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyAbstractUserDetailsAuthenticationProvider /*extends DefaultAbstractUserDetailsAuthenticationProvider*/{
    /**
     * 自定义验证
     */
    /*protected void additionalAuthenticationChecks(UserDetails userDetails, DefaultAuthenticationToken authentication) throws AuthenticationException {
        Object salt = null;
        if(this.saltSource != null) {
            salt = this.saltSource.getSalt(userDetails);
        }

        if(authentication.getCredentials() == null) {
            LOGGER.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(this.messages.getMessage("MyAbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        } else {
            String presentedPassword = authentication.getCredentials().toString();

            // 验证开始
            if("phone".equals(authentication.getType())){
                // todo 手机验证码验证，调用公共服务查询后台验证码缓存： key 为authentication.getPrincipal()的value， 并判断其与验证码是否匹配,此处写死为 1000

                if(!"1000".equals(presentedPassword)){
                    LOGGER.debug("Authentication failed: verifyCode does not match stored value");
                    throw new BadCredentialsException(this.messages.getMessage("MyAbstractUserDetailsAuthenticationProvider.badCredentials", "Bad verifyCode"));
                }
            }else if(DefaultLoginAuthenticationFilter.TYPE_QR.equals(authentication.getType())){
                // 二维码只需要根据 qrCode 查询到用户即可，所以此处无需验证
            }
            else {
                // 用户名密码验证
                if(!this.passwordEncoder.isPasswordValid(userDetails.getPassword(), presentedPassword, salt)) {
                    this.logger.debug("Authentication failed: password does not match stored value");
                    throw new BadCredentialsException(this.messages.getMessage("MyAbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
                }
            }
        }
    }

    protected final UserDetails retrieveUser(String username, MyAuthenticationToken authentication) throws AuthenticationException {
        UserDetails loadedUser;
        try {
            // 调用loadUserByUsername时加入type前缀
            loadedUser = this.getUserDetailsService().loadUserByUsername(authentication.getType() + ":" + username);
        } catch (UsernameNotFoundException var6) {
            if(authentication.getCredentials() != null) {
                String presentedPassword = authentication.getCredentials().toString();
                this.passwordEncoder.isPasswordValid(this.userNotFoundEncodedPassword, presentedPassword, (Object)null);
            }

            throw var6;
        } catch (Exception var7) {
            throw new InternalAuthenticationServiceException(var7.getMessage(), var7);
        }

        if(loadedUser == null) {
            throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
        } else {
            return loadedUser;
        }
    }*/
}
