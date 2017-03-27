package com.twogether.backend.api.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {

    @RequestMapping("/")
    public String welcome() {
        return "redirect:/web/";
    }
    
    @RequestMapping("/web")
    public String indexWeb() {
        return "index";
    }

    @RequestMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}
