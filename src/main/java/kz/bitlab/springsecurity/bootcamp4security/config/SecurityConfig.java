package kz.bitlab.springsecurity.bootcamp4security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity //будет защищать методы - сюда только админам можно, студентам нельзя. Механизм настроен. Просто нужно осилить его
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        http.formLogin()
                .loginPage("/sign-in") //страница входа будет называться так
                .loginProcessingUrl("/to-enter") //<form action = "/to-enter"> - страница куда отправить после авторизации
                .failureUrl("/sign-in") //если выйдет какая-то ошибка - return "redirect:/sign-in?error" - if error
                .defaultSuccessUrl("/profile")  //когда успешно зашел return "redirect:/profile" - if success
                .usernameParameter("user_email") //<input type = "email" name = "user_email"> - мы говорим встреть user_email - это и есть username - переписываем название инпута
                .passwordParameter("user_password"); //<input type = "password" name = "user_password>
    }
}
