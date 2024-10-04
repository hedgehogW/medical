package com.example.usermanagement.config;

import com.example.usermanagement.service.impl.MyUserDetailsService;
import com.example.usermanagement.utils.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;
    
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService)
            .passwordEncoder(passwordEncoder());
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/swagger-ui.html/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/css/**", "/js/**", "/img/**", "/index.html", "favicon.ico",
                "/doc.html", "/webjars/**", "/swagger-resources/**", "/v2/api-docs/**", "/ws/**","/swagger-ui.html").permitAll()
                .antMatchers("/hospital/**").permitAll()
                .antMatchers("/patient/**").permitAll()
                .antMatchers("/**").permitAll()
                .antMatchers("/api/auth/**").permitAll() // 允许注册和登录
                .antMatchers("/api/admin/**").hasRole("ADMIN") // 仅管理员
                .antMatchers("/api/doctors/**").hasRole("DOCTOR") // 仅医生
                .antMatchers("/api/patients/**").hasRole("PATIENT") // 仅患者
                .anyRequest().authenticated() // 其他请求需要认证
            .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
        
        // 添加JWT过滤器
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}