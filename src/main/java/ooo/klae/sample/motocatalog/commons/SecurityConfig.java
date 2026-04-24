package ooo.klae.sample.motocatalog.commons;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

import java.net.PasswordAuthentication;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .defaultSuccessUrl("/motos", true)
                .permitAll()
            );
        return http.build();
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        log.info("password: {}", encoder.encode("password"));
        return encoder;
    }

}