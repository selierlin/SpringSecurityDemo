package com.imooc.security.browser;

import com.imooc.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()//登录页的主题为表单类型
        //http.httpBasic()//登录页的主题为弹窗类型
                .loginPage("/authentication/require")//设置登录页面
                .loginProcessingUrl("/authentication/form")//登录请求处理地址
                .successHandler(imoocAuthenticationSuccessHandler)//指定登录成功处理
                .and()
                .authorizeRequests()//对请求进行授权
                .antMatchers("/authentication/require"
                        ,securityProperties.getBrowser().getLoginPage()).permitAll()//对此地址不拦截
                .anyRequest()//针对所有请求
                .authenticated()//需要身份认证
                .and().csrf().disable();//禁用CSRF
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
