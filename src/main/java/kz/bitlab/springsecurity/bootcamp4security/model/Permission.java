package kz.bitlab.springsecurity.bootcamp4security.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "t_permissions")
@Getter
@Setter
public class Permission extends BaseEntity{ //ключи от кабинета - роли

    private String role;
    private String name;
}
