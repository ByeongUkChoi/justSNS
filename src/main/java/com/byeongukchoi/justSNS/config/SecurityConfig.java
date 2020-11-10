package com.byeongukchoi.justSNS.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .headers().frameOptions().disable() // h2-console 사용하기 위해 필요
            .and()
            .csrf()
                .disable()
            .authorizeRequests()
                .antMatchers("/h2-console/**")
                    .permitAll()
                .antMatchers("/auth/signup", "/auth/signin")
                    .permitAll()
                .anyRequest()
                    .authenticated();
    }
}
