package org.restaurant.authorizationserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.web.SecurityFilterChain;

import java.util.UUID;


@Configuration
public class AuthorizationServerConfig {

    @Bean
    public RegisteredClientRepository registeredClientRepository(
            @Value("${auth.client.id}") String clientID,
            @Value("${auth.client.secret}") String clientSecret
    ) {

        RegisteredClient adminClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId(clientID)
                .clientSecret(encoder().encode(clientSecret))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .scope("order.get")
                .scope("order.post")
                .scope("order.update")
                .scope("order.delete")
                .build();

        RegisteredClient publicClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("public_client")
                .clientSecret("public_secret")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .scope("order.get")
                .scope("order.post")
                .build();

        return new InMemoryRegisteredClientRepository(adminClient, publicClient);
    }
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .oauth2AuthorizationServer((authorizationServer) -> authorizationServer
                        .oidc(Customizer.withDefaults())
                );
        return http.build();
    }
}
