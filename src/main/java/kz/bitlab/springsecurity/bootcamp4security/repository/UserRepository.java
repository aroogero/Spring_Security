package kz.bitlab.springsecurity.bootcamp4security.repository;

import jakarta.transaction.Transactional;
import kz.bitlab.springsecurity.bootcamp4security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserRepository extends JpaRepository<User, Long> {
}
