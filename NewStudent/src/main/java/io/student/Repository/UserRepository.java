package io.student.Repository;


import io.student.dto.UserDTO;
import io.student.dto.UsersDTO;
import io.student.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;


@Repository
public interface UserRepository extends JpaRepository<UserDetails, String> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_details(id,name,address,phone_number,role_id) VALUES(?1,?2,?3,?4,?5)",nativeQuery = true)
    void addUser(String id, String name, String address, String phone_number,String role_id);


    @Modifying
    @Transactional
    @Query(value = "UPDATE user_details SET name=?2,address=?3,phone_number=?4,role_id=?5 WHERE id=?1",nativeQuery = true)
    void updateUser(String id, String name, String address, String phone_number,String role_id);


    @Query(nativeQuery = true)
    Set<UsersDTO> getUserById(String id);


    @Query(value = "SELECT * FROM user_details a WHERE a.id=?1",nativeQuery = true)
    List<UserDetails> getStudentByUserId(String id);
}
