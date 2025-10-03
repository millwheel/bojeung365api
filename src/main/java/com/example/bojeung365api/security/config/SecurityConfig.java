package com.example.bojeung365api.security.config;

import com.example.bojeung365api.security.filter.RestAuthenticationFilter;
import com.example.bojeung365api.security.provider.RestAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.bojeung365api.security.AuthConstant.LOGIN_URL;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final RestAuthenticationProvider restAuthenticationProvider;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable) // TODO 나중에 활성화 판별
                .cors(AbstractHttpConfigurer::disable) // TODO 나중에 활성화 판별
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/posts/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/posts/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/posts/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/posts/**").authenticated()
                        .requestMatchers("/login", "/public/**", "/actuator/health", "/error").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(restAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private RestAuthenticationFilter restAuthenticationFilter() {
        RestAuthenticationFilter restAuthenticationFilter = new RestAuthenticationFilter(LOGIN_URL);
        ProviderManager providerManager = new ProviderManager(restAuthenticationProvider);
        restAuthenticationFilter.setAuthenticationManager(providerManager);
        return restAuthenticationFilter;
    }

//    private AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.authenticationProvider(restAuthenticationProvider);
//        return authenticationManagerBuilder.build();
//    }

}
