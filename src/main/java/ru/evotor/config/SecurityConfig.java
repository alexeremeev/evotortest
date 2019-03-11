package ru.evotor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private DataSource dataSource;

//    @Autowired
//    private RESTAuthenticationEntryPoint authenticationEntryPoint;
//
//    @Autowired
//    private RESTAuthenticationFailureHandler authenticationFailureHandler;
//
//    @Autowired
//    private RESTAuthenticationSuccessHandler authenticationSuccessHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/ico/**");
        web.debug(true);

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/add", "/goodaddretrycron*//**").permitAll()
                .and()
                .csrf().disable();
    }


}
