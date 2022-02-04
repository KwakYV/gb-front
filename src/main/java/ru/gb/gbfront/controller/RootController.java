package ru.gb.gbfront.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping
    public String getRootPath(){
        return "redirect:/product/all";
    }
}
