package com.afkl.travel.exercise.controller.securety;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/actuator/my-metrics/").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/actuator/metrics/").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/travel/location/**/").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin();


    }
    @Override
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception
    {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}admin")
                .roles("ADMIN", "USER").and().withUser("user")
                .password("{noop}user")
                .roles("USER");
    }


}