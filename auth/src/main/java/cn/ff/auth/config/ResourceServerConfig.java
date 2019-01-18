package cn.ff.auth.config;

import com.netflix.ribbon.proxy.annotation.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private AuthenticationSuccessHandler defaultLoginAuthSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler defaultLoginAuthFailureHandler;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/authorization/require")
                .loginProcessingUrl("/authorization/customer")
                .failureHandler(defaultLoginAuthFailureHandler)
                .successHandler(defaultLoginAuthSuccessHandler);
        // TODO 验证码
        http.authorizeRequests().antMatchers("/authorization/require", "/authorization/customer").permitAll()
                .anyRequest().authenticated().and().csrf().disable();

    }
}
