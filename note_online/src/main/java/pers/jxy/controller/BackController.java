package pers.jxy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : 靳新宇
 * @date : 04-30 10:46
 */
@Controller
@CrossOrigin
public class BackController {
    @GetMapping("/aaaaaaaaaaaaaaa")
    public void test() {
        int a = 1 / 0;
    }
    @GetMapping("/backHome")
    public String back() {
        return "redirect:localhost:8080/";
    }
}
