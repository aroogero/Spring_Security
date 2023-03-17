package kz.bitlab.springsecurity.bootcamp4security.repository;

import jakarta.transaction.Transactional;
import kz.bitlab.springsecurity.bootcamp4security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  //для того, чтобы вытаскивать списки(связаться с таблицей). А есть сервисы, в котором мы используем репозитори
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email); //Это sql запрос - название метода и есть SQL запрос и он делается здесь
    //SELECT * FROM t_users WHERE email =:email
}
