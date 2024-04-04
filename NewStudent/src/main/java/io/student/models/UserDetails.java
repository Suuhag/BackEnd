package io.student.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.student.dto.UsersDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;


@Entity
@SqlResultSetMapping(
        name = "usermapping",
        classes = {
                @ConstructorResult(
                        targetClass = UsersDTO.class,
                        columns = {
                                @ColumnResult(name = "id", type = String.class),
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "address", type = String.class),
                                @ColumnResult(name = "phone_number", type = String.class),
                                @ColumnResult(name = "role_id", type = String.class)

                        })
        })

@NamedNativeQuery(
        name = "UserDetails.getUserById",
        query = "SELECT u.id , u.name, u.address,u.phone_number,u.role_id FROM user_details u WHERE u.id=?1",
        resultSetMapping = "usermapping")

@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = UserDetails.class)
public class UserDetails {
    @Id
    @Column(length = 64)
    private String id;

    @Column(length = 64)
    private String name;


    @Column(length = 256)
    private String address;

    @Column(length = 256)
    private String phone_number;


//	@ManyToOne()
//	@JoinColumn
//	private UserRole user_role;

    @OneToMany(targetEntity = UserAccount.class, mappedBy = "userDetails", cascade = CascadeType.ALL)
    private Set<UserAccount> userAccount;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

//	public UserRole getRole() {
//		return user_role;
//	}
//
//	public void setRole(UserRole role) {
//		this.user_role = role;
//	}


    public Set<UserAccount> getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Set<UserAccount> userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone_number='" + phone_number + '\'' +
//				", role=" + user_role +
                ", userAccount=" + userAccount +
                '}';
    }
}
