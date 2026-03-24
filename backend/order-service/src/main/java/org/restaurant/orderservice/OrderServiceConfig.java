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

    private static final String order_path = "/api/v1/orders/**";
    private static final String menu_path = "/api/v1/menu/**";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.GET,  order_path)
                        .hasAuthority("SCOPE_order.get")
                        .requestMatchers(HttpMethod.POST, order_path)
                        .hasAuthority("SCOPE_order.post")
                        .requestMatchers(HttpMethod.PUT, order_path)
                        .hasAuthority("SCOPE_order.update")
                        .requestMatchers(HttpMethod.DELETE, order_path)
                        .hasAuthority("SCOPE_order.delete")
                        .requestMatchers(HttpMethod.GET,  menu_path)
                        .hasAuthority("SCOPE_menu.get")

                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer((auth2) -> auth2
                        .jwt(Customizer.withDefaults())
                );
        return http.build();
    }
}
