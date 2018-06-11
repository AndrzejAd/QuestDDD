package com.quest.accounts.saving.infastructure;

import com.quest.accounts.saving.domain.Email;
import com.quest.accounts.saving.domain.Account;
import com.quest.accounts.saving.domain.AccountRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataUserRepository extends AccountRepository, JpaRepository<Account, Long>  {
    Account save(Account account);
    void delete(Account account);
    List<Account> findAll();
    Optional<Account> findByEmail(Email email);
}
