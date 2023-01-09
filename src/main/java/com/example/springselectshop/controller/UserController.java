package com.example.springselectshop.controller;

import com.example.springselectshop.dto.LoginRequest;
import com.example.springselectshop.dto.SignupRequest;
import com.example.springselectshop.jwt.JwtUtil;
import com.example.springselectshop.service.KakaoService;
import com.example.springselectshop.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final KakaoService kakaoService;

    @GetMapping("/signup")
    public ModelAndView signupPage() {
        return new ModelAndView("signup");
    }

    @GetMapping("/login-page")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }


    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request) {
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

    @GetMapping("/kakao/callback")
    public String kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        // code: 카카오 서버로부터 받은 인가 코드
        String createToken = kakaoService.kakaoLogin(code, response);

        // Cookie 생성 및 직접 브라우저에 Set
        Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION_HEADER, createToken.substring(7));   // 키 , 밸류
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/api/shop";
    }



}