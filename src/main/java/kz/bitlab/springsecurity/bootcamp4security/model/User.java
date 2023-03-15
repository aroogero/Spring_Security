package kz.bitlab.springsecurity.bootcamp4security.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_users")
@Getter
@Setter
public class User extends BaseEntity{

    @Column(name = "email")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;
}
