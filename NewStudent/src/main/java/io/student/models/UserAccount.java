package io.student.models;

import io.student.dto.UserAccountDTO;
import io.student.dto.UsersDTO;
import io.student.models.compositeclass.UserAccountIds;

import javax.persistence.*;

@Entity
@SqlResultSetMapping(
        name = "useraccountmapping",
        classes = {
                @ConstructorResult(
                        targetClass = UserAccountDTO.class,
                        columns = {
                                @ColumnResult(name = "account_number",type = String.class),
                                @ColumnResult(name = "user_details_id",type = String.class),
                                @ColumnResult(name = "ifsc",type = String.class)


                        })
        })

@NamedNativeQuery(
        name = "UserAccount.getUserAccountByUserId",
        query = "SELECT u.account_number , u.user_details_id, u.ifsc FROM user_account u WHERE u.user_details_id=?1",
        resultSetMapping = "useraccountmapping")

@IdClass(UserAccountIds.class)
public class UserAccount {

    @Id
    @Column(length = 64)
    private String accountNumber;

    @MapsId
    @ManyToOne
    private UserDetails userDetails;

    @Column(length = 64)
    private String ifsc;



    public UserAccount() {
    }

    public UserAccount(String accountNumber, UserDetails userDetails, String ifsc) {
        this.accountNumber = accountNumber;
        this.userDetails = userDetails;
        this.ifsc = ifsc;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", userDetails=" + userDetails +
                ", ifsc='" + ifsc + '\'' +
                '}';
    }
}
