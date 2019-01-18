package cn.ff.auth.helper;

import cn.ff.auth.entity.UserDetailsImpl;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义token
 */
public class JwtAccessToken extends JwtAccessTokenConverter {

    private static final String BASE_USER = "INFO";

    /**
     * 生成token
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
        // 设置额外用户信息
        UserDetailsImpl baseUser = ((UserDetailsImpl) authentication.getPrincipal());
        // 将用户信息添加到token额外信息中
        Map<String, Object> map = new HashMap<>();
        map.put("username", baseUser.getUsername());
        defaultOAuth2AccessToken.getAdditionalInformation().put(BASE_USER, map);
        return super.enhance(defaultOAuth2AccessToken, authentication);

        /*Map<String, Object> info = new HashMap<>();
        info.put("userName", ((UserDetailsImpl) authentication.getPrincipal()).getUsername());
        info.put("company", "中国测试集团");
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
        return accessToken;*/
    }

    /**
     * 解析token
     */
    @Override
    public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map) {
        OAuth2AccessToken oauth2AccessToken = super.extractAccessToken(value, map);
        convertData(oauth2AccessToken, oauth2AccessToken.getAdditionalInformation());
        return oauth2AccessToken;
    }

   /* public Map<String, Object> getExtraInfo(OAuth2Authentication auth) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();

        OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());

        return accessToken.getAdditionalInformation();
    }*/

    private void convertData(OAuth2AccessToken accessToken, Map<String, ?> map) {
//        accessToken.getAdditionalInformation().put(Constant.USER_INFO,convertUserData(map.get(Constant.USER_INFO)));

    }

    /*private BaseUser convertUserData(Object map) {
        String json = JsonUtils.deserializer(map);
        BaseUser user = JsonUtils.serializable(json, BaseUser.class);
        return user;
    }*/
}
