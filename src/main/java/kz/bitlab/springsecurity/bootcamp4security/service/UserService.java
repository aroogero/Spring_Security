package kz.bitlab.springsecurity.bootcamp4security.service;

import kz.bitlab.springsecurity.bootcamp4security.model.User;
import kz.bitlab.springsecurity.bootcamp4security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Сервис пользуется всеми благами Репозитори
//Будем использовать его как Сервис, но мы его реализуем как Бин
public class UserService implements UserDetailsService {
    //UserDetailsService - один из видов сервиса, который делает аутентификацию по юзернейму
    //у него есть абстрактный метод loadUserByUsername(String username) - подтянуть юзера по его уникальной юзернейм
    //и наследуя от него, нам надо реализовать его метод
    @Autowired
    private UserRepository userRepository ;

    @Override  //переписывание
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException { //это метод сервиса, не репозитори
        //нам тут нужно возвращать пользователя по имейлу. В UserRepository у нас нет метода, который возвращает пользователя по имейлу
        User user= userRepository.findByEmail(username);
        if(user == null) throw new UsernameNotFoundException("User not found");
        return user ;
    }
    //Мы переписали метод секьюрити, который ищет пользователя под себя. Вход и выход мы переписали
    //А тут говорим, чувак, ищи пользователя по моим критериям
    //Если не писать, то секьюрити по своему будет искать пользователя, и ты не найдешь пользователя
    //я сам буду тебе передавать кого искать. Если ты найдешь туда пойдешь, если не найдешь сюда
}
