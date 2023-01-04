package com.example.springselectshop.controller;

import com.example.springselectshop.dto.LoginRequest;
import com.example.springselectshop.dto.SignupRequest;
import com.example.springselectshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    public ModelAndView signupPage() {
        return new ModelAndView("signup");
    }

    @GetMapping("/login-page")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    @PostMapping("/signup")
    public String signup(SignupRequest request) {
        userService.signup(request);
        return "redirect:/api/user/login-page";
    }

    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request, HttpServletResponse response) {
        userService.login(request, response);
        return "success";
    }

    @GetMapping("/forbidden")
    public ModelAndView getForbidden() {
        return new ModelAndView("forbidden");
    }

    @PostMapping("/forbidden")
    public ModelAndView postForbidden() {
        return new ModelAndView("forbidden");
    }

}