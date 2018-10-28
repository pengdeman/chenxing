package com.cx.chenxing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 个人中心相关
 */
@Controller
@RequestMapping("/personalcenter")
public class PersonalCenterController {


    @RequestMapping("/index")
    public String index(Model model){



        return "frontpages/personalcenter";
    }
}
