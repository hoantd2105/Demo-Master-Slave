package com.spam.repository.slave;

import com.spam.domain.slave.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountSlaveRepository extends JpaRepository<Account,Long> {

}
