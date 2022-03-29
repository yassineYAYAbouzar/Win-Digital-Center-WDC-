package com.wdc.web.wdc.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class MainController {

    @GetMapping("main")
    public String index(){
        return "hello";
    }

    @GetMapping("/profile")
    public String profile(){
        return "profile/index";
    }
    @GetMapping("management")
    public String mange(){
        return "management/index";
    }

    @GetMapping("admin")
    public String admin(){
        return "admin/index";
    }
}