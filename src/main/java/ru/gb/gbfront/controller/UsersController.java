package ru.gb.gbfront.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.api.security.dto.UserDto;
import ru.gb.gbfront.service.feign.UserManagementService;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UsersController {
    private final UserManagementService userManagementService;


    @GetMapping
    public String registerForm(Model model) {
        UserDto userDto = UserDto.builder().build();
        model.addAttribute("user", userDto);
        return "register";
    }

    @PostMapping
    public String register(UserDto userDto) {
        userManagementService.handlePost(userDto);
        return "redirect:/product/all";
    }
}
