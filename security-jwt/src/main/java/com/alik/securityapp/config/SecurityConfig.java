package com.alik.securityapp.config;

import com.alik.securityapp.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final PersonDetailsService personDetailsService;
    private final JWTFilter jwtFilter;


    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService, JWTFilter jwtFilter) {
        this.personDetailsService = personDetailsService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(auth -> auth.
                requestMatchers("/admin").
                hasRole("ADMIN"));

        http.authorizeHttpRequests(auth -> auth.
                requestMatchers("/auth/login","/auth/registration", "/error").
                permitAll().anyRequest().hasAnyRole("USER","ADMIN"));

        http.formLogin(login->login.loginPage("/auth/login").
                loginProcessingUrl("/process_login").
                defaultSuccessUrl("/hello",true).
                failureUrl("/auth/login?error"));

        http.logout(logout->logout.logoutUrl("/logout").logoutSuccessUrl("/auth/login"));

        http.sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuth = new DaoAuthenticationProvider();
        daoAuth.setUserDetailsService(personDetailsService);
        daoAuth.setPasswordEncoder(passwordEncoder());
        return daoAuth;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

}

