package com.example.springselectshop.controller;


import com.example.springselectshop.security.UserDetailsImpl;
import com.example.springselectshop.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class ShopController {

    private final FolderService folderService;

    @GetMapping("/shop")
    public ModelAndView shop() {
        return new ModelAndView("index");
    }

    // 로그인 한 유저가 메인페이지를 요청할 때 가지고있는 폴더를 반환
    @GetMapping("/user-folder")
    public String getUserInfo(Model model, HttpServletRequest request) {

        model.addAttribute("folders", folderService.getFolders(request));

        return "/index :: #fragment";    // 비동기적으로 어느 한 부분만 바꿔줌
    }

    // 로그인 한 유저가 메인페이지를 요청할 때 유저의 이름 반환
    @GetMapping("/user-info")
    @ResponseBody
    public String getUserName(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userDetails.getUsername();
    }
}