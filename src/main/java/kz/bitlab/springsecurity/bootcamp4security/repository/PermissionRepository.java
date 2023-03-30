package kz.bitlab.springsecurity.bootcamp4security.repository;

import jakarta.transaction.Transactional;
import kz.bitlab.springsecurity.bootcamp4security.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findByRole(String role); //это для того, чтобы автоматически давать новому юзеру роль юзера
}
