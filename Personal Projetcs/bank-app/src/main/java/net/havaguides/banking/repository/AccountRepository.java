package net.havaguides.banking.repository;

import net.havaguides.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {




}
