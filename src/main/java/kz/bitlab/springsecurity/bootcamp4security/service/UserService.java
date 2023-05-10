package kz.bitlab.springsecurity.bootcamp4security.service;

import kz.bitlab.springsecurity.bootcamp4security.model.Permission;
import kz.bitlab.springsecurity.bootcamp4security.model.User;
import kz.bitlab.springsecurity.bootcamp4security.repository.PermissionRepository;
import kz.bitlab.springsecurity.bootcamp4security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

//Сервис пользуется всеми благами Репозитори
//Будем использовать его как Сервис, но мы его реализуем как Бин
public class UserService implements UserDetailsService {
    //UserDetailsService - один из видов сервиса, который делает аутентификацию по юзернейму
    //у него есть абстрактный метод loadUserByUsername(String username) - подтянуть юзера по его уникальной юзернейм
    //и наследуя от него, нам надо реализовать его метод
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PermissionRepository permissionRepository; //в сервисе репозитори вызывать можно, проблем нету

    @Override  //переписывание
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //это метод сервиса, не репозитори
        //нам тут нужно возвращать пользователя по имейлу. В UserRepository у нас нет метода, который возвращает пользователя по имейлу
        User user = userRepository.findByEmail(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return user;
    }

    //Мы переписали метод секьюрити, который ищет пользователя под себя. Вход и выход мы переписали
    //А тут говорим, чувак, ищи пользователя по моим критериям
    //Если не писать, то секьюрити по своему будет искать пользователя, и ты не найдешь пользователя
    //я сам буду тебе передавать кого искать. Если ты найдешь туда пойдешь, если не найдешь сюда
    public Boolean registerUser(String email, String password, String rePassword, String fullName) {
        Boolean result = null;
        User user = userRepository.findByEmail(email);
        if (user == null) {
            result = Boolean.FALSE;
            if (password.equals(rePassword)) {
                User newUser = new User();
                newUser.setEmail(email);
                newUser.setPassword(passwordEncoder.encode(password)); //собственный готовый метод encode шифрует пароль в Бикрипт
                newUser.setFullName(fullName);
                Permission permission = permissionRepository.findByRole("ROLE_USER");
                //нам нужно не просто как объект закинуть, нужно эту роль в список завернуть - потому что у юзера роли в виде списка находятся
                List<Permission> permissionList = new ArrayList<>();
                permissionList.add(permission);
                newUser.setPermissions(permissionList);
                newUser.setPermissions(permissionList);
                userRepository.save(newUser);
                result = Boolean.TRUE;
            }
        }
        return result;
    }

    public User getCurrentUser() {  //со стороны Джава будет подтягивать пользователя из сессии
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //SecurityContextHolder - СпрингСекюровский хранитель, грубо говоря сессия секюрити
        //getContext().getAuthentication() - возвращает authentication
        if (!(authentication instanceof AnonymousAuthenticationToken)) { //говорим если он не анонимный
            return (User) authentication.getPrincipal(); //достает в виде объекта и поэтому мы его конвертируем в User
        }
        return null;
    }
}
