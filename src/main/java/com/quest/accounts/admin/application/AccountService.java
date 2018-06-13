package com.quest.accounts.admin.application;

import com.ddd.common.annotations.ApplicationService;
import com.quest.accounts.saving.domain.AccountRepository;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public void deleteAccount(final long userId){
        accountRepository
                .findById(userId)
                .ifPresent(accountRepository::delete);
    }

}
