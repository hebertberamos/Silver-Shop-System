package com.web.hevepratas.config;

import com.web.hevepratas.security.CustomUserDetailsService;
import com.web.hevepratas.security.LoginSocialSuccessHandler;
import com.web.hevepratas.servicies.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, LoginSocialSuccessHandler successHandler) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
//                .formLogin(configurer -> configurer.loginPage("/login.html").successForwardUrl("/home.html"))
                .formLogin(Customizer.withDefaults()) // Forma do usuário se autenticar (de usuários)
                .httpBasic(Customizer.withDefaults()) // Forma do usuário se autenticar (de aplicação para aplicação)
                .authorizeHttpRequests(authorize -> {
//                    authorize.requestMatchers(HttpMethod.POST, "users/**").permitAll();
                    authorize.requestMatchers(HttpMethod.GET, "products/**").permitAll();
                    authorize.anyRequest().authenticated();
                })
                .oauth2Login(oauth2 -> {
                    oauth2.successHandler(successHandler);
                })
                .build();
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder(10);
    }

//    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return new CustomUserDetailsService(userService);
    }

}
