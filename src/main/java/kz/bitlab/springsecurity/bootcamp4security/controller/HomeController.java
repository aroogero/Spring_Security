package kz.bitlab.springsecurity.bootcamp4security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping(value = "/sign-in")
    public String sinIn(Model model) {
        return "signInPage";
    }

    @GetMapping(value = "/profile")
    public String profilePage(Model model) {
            return "profilePage";
        }

}
