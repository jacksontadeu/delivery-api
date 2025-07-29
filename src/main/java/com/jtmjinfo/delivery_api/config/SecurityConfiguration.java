package com.jtmjinfo.delivery_api.config;

import com.jtmjinfo.delivery_api.security.CustomUserDetailsService;
import com.jtmjinfo.delivery_api.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(autorizacao ->{
                    autorizacao.requestMatchers("/usuario/**").permitAll();
                    autorizacao.requestMatchers("/produto/**").permitAll();
                    autorizacao.requestMatchers("/restaurante/**").permitAll();
                    autorizacao.requestMatchers("/usuario/**").hasAnyRole("ADMIN","CLIENTE");
                    autorizacao.anyRequest().authenticated();
                })
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);

    }
    @Bean
    public UserDetailsService userDetailsService(UsuarioService usuarioService) {
//        UserDetails user = User.builder()
//                .username("admin")
//                .password(encoder.encode("123456"))
//                .roles("ADMIN")
//                .build();
        return new CustomUserDetailsService(usuarioService);
    }
}
