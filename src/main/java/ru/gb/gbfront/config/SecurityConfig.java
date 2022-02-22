package ru.gb.gbfront.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ru.gb.gbfront.security.JwtConfig;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String USER_ENDPOINT = "/api/v1/user";
    public static final String LOGIN_ENDPOINT = "/api/v1/auth/login";
    private final JwtConfig jwtConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests((request)->{
            request.antMatchers("/product/all").permitAll();
            request.antMatchers("/auth/login").permitAll();
            request.antMatchers("/user").permitAll();
            request.antMatchers("/auth").permitAll();
            request.antMatchers(LOGIN_ENDPOINT).permitAll();
            request.antMatchers(HttpMethod.POST, USER_ENDPOINT).permitAll();
            request.antMatchers(USER_ENDPOINT).hasRole("ADMIN");
            request.anyRequest().authenticated();
        });

        http.apply(jwtConfig);
        http.httpBasic().disable();
        http.csrf().disable();
    }


}
