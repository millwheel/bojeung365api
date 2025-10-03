package com.example.bojeung365api.security.config;

import com.example.bojeung365api.security.filter.RestAuthenticationFilter;
import com.example.bojeung365api.security.handler.RestAuthenticationFailureHandler;
import com.example.bojeung365api.security.handler.RestAuthenticationSuccessHandler;
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
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import static com.example.bojeung365api.security.AuthConstant.LOGIN_URL;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final RestAuthenticationProvider restAuthenticationProvider;
    private final RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;
    private final RestAuthenticationFailureHandler restAuthenticationFailureHandler;

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
                        .requestMatchers("/sign-up","/login", "/public/**", "/actuator/health", "/error").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(restAuthenticationFilter(http), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private RestAuthenticationFilter restAuthenticationFilter(HttpSecurity http) {
        RestAuthenticationFilter restAuthenticationFilter = new RestAuthenticationFilter(LOGIN_URL);
        ProviderManager providerManager = new ProviderManager(restAuthenticationProvider);
        restAuthenticationFilter.setAuthenticationManager(providerManager);
        restAuthenticationFilter.setAuthenticationSuccessHandler(restAuthenticationSuccessHandler);
        restAuthenticationFilter.setAuthenticationFailureHandler(restAuthenticationFailureHandler);
        restAuthenticationFilter.setSecurityContextRepository(securityContextRepository(http));
        return restAuthenticationFilter;
    }

    private SecurityContextRepository securityContextRepository(HttpSecurity http) {
        SecurityContextRepository securityContextRepository = http.getSharedObject(SecurityContextRepository.class);
        if (securityContextRepository == null) {
            securityContextRepository = new DelegatingSecurityContextRepository(
                    new RequestAttributeSecurityContextRepository(),
                    new HttpSessionSecurityContextRepository()
            );
        }
        return securityContextRepository;
    }

}
