package com.quest.mails.domain;

import com.ddd.common.domain.AbstractEntity;
import com.ddd.common.validation.Contract;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailSendingInfo extends AbstractEntity {
    private String address;
    private String subject;
    private LocalDate sendDate;

    public EmailSendingInfo(String address, String subject) {
        Contract.notNull(address, subject);
        this.address = address;
        this.subject = subject;
        this.sendDate = LocalDate.now();
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
