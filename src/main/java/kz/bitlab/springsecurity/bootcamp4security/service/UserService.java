package kz.bitlab.springsecurity.bootcamp4security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service //прямая связь, когда ты с имплементацией в виде простого класса пишешь - без интерфейса
public class UserService implements UserDetailsService {
//UserDetailsService - один из видов сервиса, который делает аутентификацию по юзернейму
    //у него есть абстрактный метод loadUserByUsername(String username) - подтянуть юзера по его уникальной юзернейм
    //и наследуя от него, нам надо реализовать его метод

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    }
}
