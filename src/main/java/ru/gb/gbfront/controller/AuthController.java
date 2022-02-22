package ru.gb.gbfront.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.api.security.dto.AuthenticationUserDto;
import ru.gb.api.security.dto.UserDto;
import ru.gb.gbfront.config.TokenHandler;
import ru.gb.gbfront.service.feign.AuthService;
import ru.gb.gbfront.service.feign.UserManagementService;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Resource(name = "tokenHandler")
    TokenHandler tokenHandler;

    @GetMapping
    public String showForm(Model model){
        AuthenticationUserDto authenticationUserDto = AuthenticationUserDto.builder().build();
        model.addAttribute("auth", authenticationUserDto);
        return "login";
    }



    @PostMapping("/login")
    public String login(AuthenticationUserDto authenticationUserDto) {
        ResponseEntity<?> responseEntity = authService.handlePost(authenticationUserDto);
        Map<String, String> map = (Map<String, String>) responseEntity.getBody();
        tokenHandler.setToken(map.get("token"));
        return "redirect:/product/all";
    }
}
