package ru.gb.gbfront.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.gb.api.security.dto.AuthenticationUserDto;

import java.util.Map;

@FeignClient(url = "localhost:8484/api/v1/auth",  name = "authRestController")
public interface AuthService {

    @PostMapping("/login")
    ResponseEntity<? extends Map<Object, Object>> handlePost(@Validated @RequestBody AuthenticationUserDto authenticationUserDto);
}
