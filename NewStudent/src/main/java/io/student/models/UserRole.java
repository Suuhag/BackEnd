package io.student.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = UserRole.class)
public class UserRole {

    @Id
    @Column(length = 64)
    private String id;


    @Column
    private String role;

//    @OneToMany(mappedBy = "user_role", cascade = CascadeType.ALL)
//    private Set<UserDetails> user;

    @OneToMany(mappedBy = "user_role", cascade = CascadeType.DETACH)
    private Set<Student> student;


    public UserRole() {
    }

    public UserRole(String id, String role) {
        this.id = id;
        this.role = role;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public Set<Student> getStudent() {
        return student;
    }

    public void setStudent(Set<Student> student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id='" + id + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
