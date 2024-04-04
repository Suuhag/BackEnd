package io.student.Repository;

import io.student.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users,String> {


    @Query(value = "SELECT COUNT(*) FROM users WHERE name = ?1 AND password = ?2", nativeQuery = true)
    Integer checkForCredentials(String name, String password);

//    @Query(value = "SELECT name,password FROM users WHERE id = ?1", nativeQuery = true)
//    List<Users> getUserById(String id);

    @Query(value = "SELECT * FROM users u WHERE u.id = ?1", nativeQuery = true)
    List<Users> getUserById(String id);

}
