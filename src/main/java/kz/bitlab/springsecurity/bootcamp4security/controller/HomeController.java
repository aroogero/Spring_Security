package kz.bitlab.springsecurity.bootcamp4security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("isAuthenticated()") //Защищаем методы - сюда может входить только если авторизовался
    @GetMapping(value = "/profile")
    public String profilePage(Model model) {
        return "profilePage";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')") //Защищаем методы - сюда может входить только админ
    @GetMapping(value = "/admin")
    public String admin(Model model) {
        return "admin";
    }

    @GetMapping(value = "/403")
    public String accessDenied(Model model) {
        return "accessDenied";
    }

}
