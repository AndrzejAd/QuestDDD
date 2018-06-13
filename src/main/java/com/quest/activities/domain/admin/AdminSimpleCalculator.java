package com.quest.activities.domain.admin;

import com.ddd.common.annotations.DomainService;

@DomainService
public class AdminSimpleCalculator implements AdminControlPanel{

    @Override
    public double grantBonus(double bonus) {
        return bonus;
    }
}
