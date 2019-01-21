package cn.ff.auth.controller;

import cn.ff.auth.helper.JwtAccessToken;
import cn.ff.common.entity.SysUser;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private JwtAccessTokenConverter jwtAccessToken;
    @Autowired
    private TokenStore tokenStore;

    @GetMapping("/current")
    public Principal getUser(Principal principal) {
        return principal;
    }
    @GetMapping("/me")
    public Object me(Authentication user) {
        return user;
    }

    @PreAuthorize("#oauth2.hasScope('server')")
    @GetMapping("sss")
    public String createUser() {
        return "u are sb ";
    }
    @GetMapping("aaa")
    public String getAdmin() {
        return "u are admin ";
    }

    @GetMapping("/token")
    public Map<String, Object> token(OAuth2Authentication auth){
        Map map = Maps.newHashMap();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
        return accessToken.getAdditionalInformation();
    }
}
