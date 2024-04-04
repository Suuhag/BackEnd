package io.student.models.compositeclass;

import io.student.models.UserDetails;

import java.io.Serializable;
import java.util.Objects;

public class UserAccountIds implements Serializable {

    private String accountNumber;
    private UserDetails userDetails;


    public UserAccountIds(String accountNumber, UserDetails userDetails) {
        this.accountNumber = accountNumber;
        this.userDetails = userDetails;
    }

    public UserAccountIds() {
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

    @Override
    public String toString() {
        return "UserAccountIds{" +
                "accountNumber='" + accountNumber + '\'' +
                ", userDetails=" + userDetails +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccountIds)) return false;
        UserAccountIds that = (UserAccountIds) o;
        return getAccountNumber().equals(that.getAccountNumber()) && getUserDetails().equals(that.getUserDetails());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountNumber(), getUserDetails());
    }
}
