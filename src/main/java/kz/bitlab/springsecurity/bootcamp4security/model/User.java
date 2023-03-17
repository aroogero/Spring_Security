package kz.bitlab.springsecurity.bootcamp4security.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "t_users")
@Getter
@Setter
public class User extends BaseEntity implements UserDetails {

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @ManyToMany(fetch = FetchType.EAGER) //по умолчанию всегда равняется LAZY
    //Когда подтяшиваем юзера, из-за LAZY, он Permission не подтягивает, он становится нуллом
    //EAGER(мгновенно) подтягивает всю таблицу
    //при Секьюрити аутентикейшн нам нужен Игер чтобы мы мгновенно и Юзер с Пермишноном подтянули, чтобы определиться с его ролью
    private List<Permission> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions;  //permissions является GrantedAuthority, так как в его классе мы заимплементировали и реализовали метод ГрантедАуторити
    }

    @Override
    public String getUsername() { //уникальный пользовательский id если бы мы спрашивали ИИн то написали бы ИИН
        return email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
