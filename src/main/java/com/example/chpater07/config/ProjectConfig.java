package com.example.chpater07.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {


    public static final String ADMIN = "ADMIN";
    public static final String MANAGER = "MANAGER";

    @Bean
    public UserDetailsService userDetailsService() {
        var manage = new InMemoryUserDetailsManager();

        var user1 = User.withUsername("john")
                .password("12345")
                .roles(ADMIN)
                .build();


        var user2 = User.withUsername("jane")
                .password("12345")
                .roles(MANAGER)
                .build();

        manage.createUser(user1);
        manage.createUser(user2);

        return manage;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic();

        http.authorizeRequests(
                (auth) ->
                        auth
                                .mvcMatchers("/hello").hasRole(ADMIN)
                                .mvcMatchers("/caio").hasRole(MANAGER)
        );


        return http.build();
    }

}
