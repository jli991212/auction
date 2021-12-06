package com.DBProject.auctionSystem.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery(
                "SELECT email, CONCAT('{noop}',password), TRUE enabled FROM member WHERE email=?"
            )
            .authoritiesByUsernameQuery(
                "SELECT email, memberType authority FROM member WHERE email=?"
            );
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // httpSecurity.authorizeRequests()
        //   .antMatchers("/auctions")
        //   .hasAuthority("buyer")
        //   .anyRequest()
        //   .authenticated()
        //   .and()
        //   .formLogin();
        
        // httpSecurity.csrf()
        //   .ignoringAntMatchers("/h2-console/**");

        // httpSecurity.authorizeRequests()
        //     .anyRequest()
        //     .authenticated()
        //     .and()
        //     .formLogin()
        //         .loginPage("/login")
        //         .usernameParameter("email")
        //         .permitAll()
        //     .and()
        //     .logout().permitAll();

        // httpSecurity.authorizeRequests()
        //     .antMatchers("/resources/**")
        //         .permitAll()
        //         .anyRequest()
        //         .authenticated()
        //     .and()
        //     .formLogin().loginPage("/login")
        //     .loginProcessingUrl("/authentication").permitAll();
        
        httpSecurity.authorizeRequests()
            .antMatchers("/", "/login", "/logout", "/webjars/**", "/auctions")
                .permitAll()
                .anyRequest()
            .authenticated()
            .and()
            .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .permitAll()
            .and()
            .logout().permitAll();
            
        httpSecurity.headers()
          .frameOptions()
          .sameOrigin();
    }
}
