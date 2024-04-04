package io.student.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import javax.persistence.*;
import java.math.BigInteger;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Student.class)
public class Student {


    @Id
    @Column(length = 64)
    private String id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String phone_number;

    @Column
    private String address;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private UserRole user_role;

    @Column
    private String father_name;

    @Column
    private BigInteger created_at;

    @Column
    private BigInteger updated_at;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserRole getUserRole() {
        return user_role;
    }

    public void setUserRole(UserRole userRole) {
        this.user_role = userRole;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public BigInteger getCreated_at() {
        return created_at;
    }

    public void setCreated_at(BigInteger created_at) {
        this.created_at = created_at;
    }

    public BigInteger getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(BigInteger updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", address='" + address + '\'' +
                ", user_role=" + user_role +
                ", father_name='" + father_name + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
