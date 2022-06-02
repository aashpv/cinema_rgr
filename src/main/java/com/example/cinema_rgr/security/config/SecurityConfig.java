package com.example.cinema_rgr.security.config;

import com.example.cinema_rgr.security.details.UserDetailsServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImple userDetailsServiceImple;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and()
                .authorizeRequests()
                .antMatchers(
                        "/registration",
                        "/login",
                        "/today",
                        "/",
                        "/tickets/**",
                        "/tomorrow/**",
                        "/dayOfMonth/**",
                        "/movies/**")
                .permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/movies/delete").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("email")
                .defaultSuccessUrl("/today")
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/signOut")// Настроить адрес выхода
                .logoutSuccessUrl("/today")// После выхода переходим на страницу регистрации
                .deleteCookies("JSESSIONID");
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsServiceImple)
                .passwordEncoder(passwordEncoder);
    }
}
