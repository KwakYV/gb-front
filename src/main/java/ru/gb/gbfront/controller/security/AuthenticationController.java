package ru.gb.gbfront.controller.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.api.security.dto.AuthenticationUserDto;
import ru.gb.gbfront.config.TokenHandler;
import ru.gb.gbfront.entity.security.AccountUser;
import ru.gb.gbfront.security.JwtTokenProvider;
import ru.gb.gbfront.service.security.UserService;

import javax.annotation.Resource;
import java.util.HashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationUserDto authenticationUserDto) {
        AccountUser accountUser = userService.findByUsername(authenticationUserDto.getUsername());

        String token = jwtTokenProvider.createToken(accountUser.getUsername(), accountUser.getRoles());
        HashMap<Object, Object> authMap = new HashMap<>();
        authMap.put("username", accountUser.getUsername());
        authMap.put("token", token);
        ResponseCookie responseCookie = ResponseCookie.from("user-id", token)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(60)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.COOKIE, responseCookie.toString());
        return new ResponseEntity<>(authMap, headers, HttpStatus.OK);
    }

}
