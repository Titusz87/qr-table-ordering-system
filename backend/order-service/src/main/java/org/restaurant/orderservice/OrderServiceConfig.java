package org.restaurant.orderservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class OrderServiceConfig {

    private static final String path = "/api/v1/orders/**";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.GET,  path)
                        .hasAuthority("SCOPE_order.get")
                        .requestMatchers(HttpMethod.POST, path)
                        .hasAuthority("SCOPE_order.post")
                        .requestMatchers(HttpMethod.PUT, path)
                        .hasAuthority("SCOPE_order.update")
                        .requestMatchers(HttpMethod.DELETE, path)
                        .hasAuthority("SCOPE_order.delete")

                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer((auth2) -> auth2
                        .jwt(Customizer.withDefaults())
                );
        return http.build();
    }
}
