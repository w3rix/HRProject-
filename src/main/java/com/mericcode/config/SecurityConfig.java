package com.mericcode.config;

import com.mericcode.jwt.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.Filter;
import org.springframework.web.filter.CommonsRequestLoggingFilter;


@Configuration
public class SecurityConfig {

    private final JwtService jwtService;

    public SecurityConfig(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {return  new BCryptPasswordEncoder();}


    @Configuration
    public class RequestLoggingConfig {

        @Bean
        public Filter requestLoggingFilter() {
            CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
            filter.setIncludeClientInfo(true);
            filter.setIncludeQueryString(true);
            filter.setIncludePayload(true);
            filter.setIncludeHeaders(false);
            filter.setMaxPayloadLength(10000);
            return filter;
        }
    }
}
