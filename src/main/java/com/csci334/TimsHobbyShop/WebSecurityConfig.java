package com.csci334.TimsHobbyShop;

import com.csci334.TimsHobbyShop.repository.UserDetails_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public UserDetails_Service userDetails_service;

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.authenticationProvider(authenticationProvider());
        //builder.userDetailsService(userDetails_service);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetails_service);
        authProvider.setPasswordEncoder(new PlaintextPasswordEncoder());
        return authProvider;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/img/**","/js/**","/css/**", "/Login/**").permitAll()
                .anyRequest().authenticated();

        http.formLogin().loginPage("/Login")
                .loginProcessingUrl("/Login/")
                .permitAll();
//                .and()
//                .formLogin().loginPage("/Login")
//                .successForwardUrl("/Customer").permitAll()
//                .and()
//                .logout().permitAll();
//
		http
				.authorizeRequests()
				.antMatchers("/Customer/Register")
				.hasRole("Admin");
    }
}
