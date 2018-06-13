package com.quest.accounts.admin.ui;

import com.quest.accounts.admin.application.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AccountsRestController {
    private final AccountService accountService;

    @RequestMapping(value = "/deleteAccount", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAccount(@RequestParam("id") final long userId) {
        accountService.deleteAccount(userId);
        return new ResponseEntity<>("Done!", HttpStatus.OK);
    }

}
