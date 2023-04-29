package kz.bitlab.springsecurity.bootcamp4security.config;

import kz.bitlab.springsecurity.bootcamp4security.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity //будет защищать методы - сюда только админам можно, студентам нельзя. Механизм настроен. Просто нужно осилить его
public class SecurityConfig {

    @Bean
    public UserService userService() { //превратили в Бин, для того тобы использовать его
        return new UserService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() { //это такой интерфейс, который говорит какой вид алгоритма ты будешь использовать
        return new BCryptPasswordEncoder(); //это будет моим шифровальщиком
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            AuthenticationManagerBuilder authenticationManagerBuilder =
                    http.getSharedObject(AuthenticationManagerBuilder.class); //способ инициализации в Spring Security
            authenticationManagerBuilder.userDetailsService(userService()) //userService - вот этот чувак, будет его userDetailsService-ом
                    .passwordEncoder(passwordEncoder()); //моим паролевским проверятелям будет passwordEncoder
        //Если выйдет ошибка с доступом, перенаправит на эту страницу
        http.exceptionHandling().accessDeniedPage("/403"); //страница которая скажет ДОСТУП ЗАПРЕЩЕН - ее сами придумали - это наш собственный контроллер

        http.formLogin()
                .loginPage("/sign-in") //страница входа будет называться так
                .loginProcessingUrl("/to-enter") //<form action = "/to-enter"> - страница куда отправить после авторизации
                .failureUrl("/sign-in?userError") //если выйдет какая-то ошибка - return "redirect:/sign-in?error" - if error
                .defaultSuccessUrl("/profile")  //когда успешно зашел return "redirect:/profile" - if success
                .usernameParameter("user_email") //<input type = "email" name = "user_email"> - мы говорим встреть user_email - это и есть username - переписываем название инпута
                .passwordParameter("user_password"); //<input type = "password" name = "user_password>

        http.logout() //прекращает сессию
                .logoutUrl("/to-exit")  //отправляем Пост запрос в to exit чтобы выйти - этого недостаточно, он не должен там застрять
                .logoutSuccessUrl("/sign-in");

        http.csrf().disable(); //уникальный токен для mvc паттерна - генерирует, чтобы мы не отправляли Пост запрос
        return http.build();
    }
}
