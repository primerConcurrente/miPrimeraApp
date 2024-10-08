package io.primerconcurrente.mi_primeraapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
    //Debería extender  WebSecurityConfigurerAdapter pero actualmente no funciona correctamente

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}password").roles("ADMIN")
                .and()
                .withUser("user").password("{noop}password").roles("USER");
    }

    protected void configure(HttpSecurity http) throws Exception {
        /*http
                .authorizeRequests()
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                .mvcMatchers("/user/**").hasRole("USER")
                .and()
                .formLogin();

         */ //actualmente no funciona correctamente
    }
}
