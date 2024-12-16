package com.example.onemoreboard.controller;

import org.aspectj.weaver.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName    : com.example.onemoreboard.controller
 * fileName       : Hello
 * author         : JAEIK
 * date           : 12/13/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/13/24       JAEIK       최초 생성
 */
@Controller
public class Hello {
    @GetMapping("/hi")
    public String getHello(Model model) {
        model.addAttribute("hello", "안녕!!");
        return "hi";
    }
    @GetMapping("/kk")
    public String getRandDom(Model model) {
        String[] girlName = {
                "이새봄",
                "이혜린",
                "야마토"
        };
        int randInt = (int) (Math.random() * girlName.length);
        model.addAttribute("data", girlName[randInt]);
        return "random";
    }
}
