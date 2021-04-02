package pers.jxy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : 靳新宇
 * @date : 03-28 20:17
 */
@Controller
public class TestController {
    @GetMapping("/adminRegister")
    public String adminRegister() {
        return "adminRegister";
    }

    @GetMapping("/test1")
    public void test1(HttpServletResponse resp) {
        try {
            resp.sendRedirect("http://localhost:8080/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
