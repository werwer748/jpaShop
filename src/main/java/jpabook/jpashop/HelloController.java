package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello")
    //? Model: Spring에서 제공하는 객체로, 데이터를 View로 넘길 수 있음
    public String hello(Model model) {
        model.addAttribute("data", "hello!!!");
        return "hello";
    }
}
