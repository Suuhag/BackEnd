package io.student.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import io.student.Repository.StudentRepository;
import io.student.Repository.UserRepository;
import io.student.dto.StudentDTO;
import io.student.models.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.QueryExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalTime;
import java.util.*;

@Slf4j
@Service
public class StudentService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    DataSource dataSource;

    @Autowired
    RoleService roleService;


//    public StudentService(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }


    // "Batch Updates" or "Batch Processing"
    public void addStudents(List<StudentDTO> studentDTOList) {
        try {
            String sql = "UPDATE student SET address=? , email=? , name =? , phone_number=? WHERE id = ?";
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for (StudentDTO stud : studentDTOList) {

                preparedStatement.setString(5, stud.getId());
                preparedStatement.setString(1, stud.getAddress());
                preparedStatement.setString(2, stud.getEmail());
                preparedStatement.setString(3, stud.getName());
                preparedStatement.setString(4, stud.getPhone_number());

                preparedStatement.addBatch();
                System.out.println(preparedStatement);
            }
            preparedStatement.executeBatch();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //entitymanager
    @Transactional
    public void addStudents2(List<StudentDTO> studentDTOList) {
        try {

            for (StudentDTO stud : studentDTOList) {

                Student student = new Student();
                student.setId(stud.getId());
                student.setName(stud.getName());
                student.setEmail(stud.getEmail());
                student.setPhone_number(stud.getPhone_number());
                student.setAddress(stud.getAddress());

                entityManager.merge(student);
//                entityManager.persist(student);
//                entityManager.flush();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String generateUniqueId() {
        return UUID.randomUUID().toString();
    }


    public static String generateTenDigitNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(1000000000); // Generate a random number between 0 and 999,999,999
        String formattedNumber = String.format("%010d", randomNumber); // Format as a 10-digit string with leading zeros
        return formattedNumber;
    }

    public void addStudentsList() {
        List<Student> studentlist = new ArrayList<>();
        BigInteger current_timestamp = BigInteger.valueOf(System.currentTimeMillis());
        for (Integer i = 0; i <= 10000; i++) {
            Student student = new Student();

            student.setId(i.toString());
            student.setName("Rama" + i);
            student.setUserRole(roleService.getroleById("1"));
            student.setEmail("Rama" + i + "@gmail.com");
            student.setAddress(generateUniqueId());
            student.setFather_name("Lakshnama" + i);
            student.setPhone_number("1234567890");
            student.setCreated_at(current_timestamp);

            studentlist.add(student);

        }
        studentRepository.saveAll(studentlist);
    }


    public String updateStudentList() {
        try {
            List<Student> studentList = studentRepository.findAll();

            List<Student> updateStudentlist = new ArrayList<>();

            for (Student student : studentList) {
                System.out.println(" Student :" + student);
                BigInteger current_timestamp = BigInteger.valueOf(System.currentTimeMillis());
                student.setPhone_number(generateTenDigitNumber());
                student.setUpdated_at(current_timestamp);
                student.setAddress(generateUniqueId());

                updateStudentlist.add(student);
            }
            System.out.println("List size : " + updateStudentlist.size());
            studentRepository.saveAll(updateStudentlist);

            return "update successful";
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
    }

    //"Batch Updates" or "Batch Processing"
    public void updateStudents() {
        try {
            String sql = "UPDATE student SET address=? , phone_number=?,updated_at=? WHERE id = ?";
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            List<Student> studentList = studentRepository.findAll();
            BigInteger current_timestamp = BigInteger.valueOf(System.currentTimeMillis());

            for (Student stud : studentList) {

                preparedStatement.setString(4, stud.getId());
                preparedStatement.setString(1, "abc");
                preparedStatement.setString(2, "abc");
                preparedStatement.setString(3, String.valueOf(current_timestamp));

                preparedStatement.addBatch();
                preparedStatement.clearBatch();
                System.out.println("query : " + preparedStatement);
            }
            preparedStatement.setString(4, "5");
            preparedStatement.setString(1, generateUniqueId());
            preparedStatement.setString(2, generateTenDigitNumber());
            preparedStatement.setString(3, String.valueOf(current_timestamp));
            preparedStatement.addBatch();
            System.out.println("query : " + preparedStatement);

            System.out.println("List size : " + studentList.size() + "   current time :" + LocalTime.now());
            preparedStatement.executeBatch();
            System.out.println("List size1 : " + studentList.size() + "   current time :" + LocalTime.now());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void updateStudentUsingNativeQuery() {
        List<Student> studentList = studentRepository.findAll();
        System.out.println(" size :" + studentList.size());
        System.out.println("start time : " + LocalTime.now());
        int i = 0;
        for (Student stud : studentList) {
            BigInteger current_timestamp = BigInteger.valueOf(System.currentTimeMillis());
            studentRepository.updateStudent(generateUniqueId(), generateTenDigitNumber(), String.valueOf(current_timestamp), stud.getId());
            System.out.println(i++);
        }
        System.out.println("Students updated successfully");
        System.out.println("end time : " + LocalTime.now());

    }


    public void multiMapTestMethod() {
        Multimap<String, String> measuringInstrumentsMap = ArrayListMultimap.create();

        measuringInstrumentsMap.put("1", "abc");
        measuringInstrumentsMap.put("1", "def");
        measuringInstrumentsMap.put("2", "ghi");

        System.out.println(" multimap :" + measuringInstrumentsMap);

        Collection<String> measuringInstruments = new ArrayList<>();
        measuringInstruments = measuringInstrumentsMap.get("1");
        System.out.println("1 :" + measuringInstruments);

        for (String mi : measuringInstruments) {
            System.out.println(mi);
        }
    }

}
