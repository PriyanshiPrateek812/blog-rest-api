package com.thepriyanshiprateek.blog_rest_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SwaggerConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeRequests(authorizeRequests->
                        authorizeRequests
                                .anyRequest()
                                .authenticated()
                ).httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails mark = User.builder()
                .username("mark")
//                .password("123")
                .password(passwordEncoder().encode("123"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
//                .password("456")
                .password(passwordEncoder().encode("456"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(mark, admin);
    }


}
