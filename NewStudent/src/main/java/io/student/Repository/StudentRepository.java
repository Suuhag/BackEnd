package io.student.Repository;

import io.student.models.Student;
import io.student.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {


    @Modifying
    @Transactional
    @Query(value = "UPDATE student SET address=?1 , phone_number=?2,updated_at=?3 WHERE id = ?4", nativeQuery = true)
    void updateStudent(String address, String phone_number, String updated_at, String id);

}
