package cn.ff.auth.token;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 二维码Token
 */
public class QrAuthenticationToken extends DefaultAuthenticationToken{

    public QrAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public QrAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
