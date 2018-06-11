package com.quest.accounts.saving.domain;

import java.util.Collection;
import java.util.Optional;

public interface AccountRepository {
    Account save(Account account);
    void delete(Account account);
    Collection<Account> findAll();
    Optional<Account> findByEmail(Email email);
}
