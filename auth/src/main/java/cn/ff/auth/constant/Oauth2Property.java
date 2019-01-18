package cn.ff.auth.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Data
@Component
@ConfigurationProperties(prefix="oauth2")
public class Oauth2Property {

    private TokenProperty token = new TokenProperty();












}
