package kz.bitlab.springsecurity.bootcamp4security.controller;

import kz.bitlab.springsecurity.bootcamp4security.model.Post;
import kz.bitlab.springsecurity.bootcamp4security.service.PostService;
import kz.bitlab.springsecurity.bootcamp4security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

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

    @GetMapping(value = "/sign-up")
    public String signUp(Model model) {
        return "registerPage";
    }

    @PostMapping(value = "/to-register")
    public String toRegister(@RequestParam(name = "user_email") String email,
                             @RequestParam(name = "user_password") String password,
                             @RequestParam(name = "user_re_password") String rePassword,
                             @RequestParam(name = "user_full_name") String fullName) {

        Boolean result = userService.registerUser(email, password, rePassword, fullName);
        if (result != null) {
            if (result.equals(Boolean.TRUE)) { //можно и так result == Boolean.TRUE
                return "redirect:/sign-up?success";
            } else {
                return "redirect:/sign-up?passworderror";
            }
        } else {
            return "redirect:/sign-up?usererror";
        }
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/add-news")
    public String addPost(Post post) { //здесь неизвестен кто автор при добавлении поста
        postService.createPost(post);
        return "redirect:/admin?success";
    }
}
