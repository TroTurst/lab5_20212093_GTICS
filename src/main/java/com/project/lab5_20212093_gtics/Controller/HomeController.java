package com.project.lab5_20212093_gtics.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String Principal(){
        return "principal";
    }
}
