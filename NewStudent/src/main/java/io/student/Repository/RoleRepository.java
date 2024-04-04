package io.student.Repository;

import io.student.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<UserRole,String> {


    @Query(value = "SELECT * FROM user_role where id =?1", nativeQuery = true)
    List<UserRole> getRoleById(String id);




}
