package com.quest.activities.domain.enticer;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Getter
public abstract class Enticer {
    protected List<EnticerObserver> enticmentObservers;

    @Autowired
    public Enticer(List<EnticerObserver> enticmentObservers) {
        this.enticmentObservers = enticmentObservers;
    }

    public void notifyAllObserversAbountBonus(){
        enticmentObservers.forEach(enticerObserver -> enticerObserver.addBonusToExperienceGain(enticeActivityMultiplier()));
    }

    public void deleteObserver(EnticerObserver enticerObserver){
        enticmentObservers.remove(enticerObserver);
    }

    abstract double enticeActivityMultiplier();
}
