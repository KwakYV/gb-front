package ru.gb.gbfront.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.annotation.SessionScope;
import ru.gb.gbfront.service.feign.AuthService;
import ru.gb.gbfront.service.feign.UserManagementService;


@Configuration
@EnableFeignClients(clients = {AuthService.class, UserManagementService.class})
public class ShopConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @SessionScope
    public TokenHandler tokenHandler() {
        return new TokenHandler();
    }

}
