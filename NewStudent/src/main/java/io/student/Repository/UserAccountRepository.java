package io.student.Repository;

import io.student.dto.UserAccountDTO;
import io.student.dto.UsersDTO;
import io.student.models.UserAccount;
import io.student.models.compositeclass.UserAccountIds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, UserAccountIds> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_account(account_number,user_details_id,ifsc) VALUES(?1,?2,?3)",nativeQuery = true)
    void addUserAccount(String account_number, String user_details_id, String ifsc);

    @Query(value = "SELECT COUNT(*) FROM user_account WHERE account_number = ?1 AND user_details_id = ?2", nativeQuery = true)
    Integer checkForAttribute(String account_number, String user_details_id);


    @Modifying
    @Transactional
    @Query(value = "Update user_account SET ifsc=?3 WHERE account_number=?1 AND user_details_id=?2",nativeQuery = true)
    void updateUserAccount(String account_number, String user_details_id, String ifsc);

    @Query(nativeQuery = true)
    Set<UserAccountDTO> getUserAccountByUserId(String userId);
}
