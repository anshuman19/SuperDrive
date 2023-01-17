package com.udacity.jwdnd.spring_security_basics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/result")
@Controller
public class ResultController {
    @GetMapping()
    public String getResultPage(Model model){
        return "result";
    }
}
