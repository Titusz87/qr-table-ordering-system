package org.restaurant.orderservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class OrderServiceConfig {

    private static final String order_path = "/api/v1/order/**";
    private static final String menu_path = "/api/v1/menu/**";
    private static final String session_path = "/api/v1/session/**";


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .cors((cors) -> cors
                        .configurationSource(clientApplicationConfiguration())
                )
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.GET,  order_path)
                        .hasAuthority("SCOPE_order.get")
                        .requestMatchers(HttpMethod.POST, order_path)
                        .hasAuthority("SCOPE_order.post")
                        .requestMatchers(HttpMethod.PUT, order_path)
                        .hasAuthority("SCOPE_order.update")
                        .requestMatchers(HttpMethod.DELETE, order_path)
                        .hasAuthority("SCOPE_order.delete")
                        .requestMatchers(HttpMethod.GET,  menu_path).permitAll()
                        //.hasAuthority("SCOPE_menu.get")
                        .requestMatchers(HttpMethod.POST, session_path).permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer((auth2) -> auth2
                        .jwt(Customizer.withDefaults())
                );
        return http.build();
    }

    @Bean
    UrlBasedCorsConfigurationSource clientApplicationConfiguration() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Configuration
    public class AppConfig {

        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }
}
