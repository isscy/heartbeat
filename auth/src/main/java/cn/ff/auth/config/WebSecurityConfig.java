package cn.ff.auth.config;

import cn.ff.auth.handler.DefaultLoginAuthSuccessHandler;
import cn.ff.auth.helper.DefaultLoginAuthenticationFilter;
import cn.ff.auth.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http    // 配置登陆页/login并允许访问
            .formLogin().permitAll()
            // 登出页
            .and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
            // 其余所有请求全部需要鉴权认证
            .and().authorizeRequests().anyRequest().authenticated()
            // 由于使用的是JWT，我们这里不需要csrf
            .and().csrf().disable();*/
            http
                .addFilterBefore(defaultLoginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                //.formLogin().loginPage("/authorization/login").permitAll()
                .authorizeRequests()
                .antMatchers("/authorization/login").permitAll()
                //.and().logout().logoutUrl("/logout").logoutSuccessUrl("/backReferer")
                .anyRequest().authenticated()
                .and().csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/authorization/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 自定义登陆过滤器
     */
    @Bean
    public DefaultLoginAuthenticationFilter defaultLoginAuthenticationFilter() throws Exception {
        DefaultLoginAuthenticationFilter filter = new DefaultLoginAuthenticationFilter();
        filter.setAuthenticationManager(this.authenticationManagerBean());
        //filter.setAuthenticationSuccessHandler(new DefaultLoginAuthSuccessHandler());
        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error"));
        return filter;
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider1 = new DaoAuthenticationProvider();
        // 设置userDetailsService
        provider1.setUserDetailsService(userDetailsService);
        // 禁止隐藏用户未找到异常
        //provider1.setHideUserNotFoundExceptions(false);
        // 使用BCrypt进行密码的hash
        provider1.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider1;
    }
}
