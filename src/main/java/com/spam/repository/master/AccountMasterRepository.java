package com.spam.repository.master;

import com.spam.domain.master.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMasterRepository extends JpaRepository<Account,Long> {

}
