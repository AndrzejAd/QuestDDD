package com.todo.activities.domain;

import com.todo.registering.saving.domain.Account;
import com.todo.registering.saving.domain.Address;
import com.todo.registering.saving.domain.Email;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DataJpaTest
public class ActivitiesTest {

    @Autowired
    private TestEntityManager testEntityManager;

    private User user;
    private ActivityType testActivityType;

    @BeforeEach
    public void setUpUser(){
        Account account = createUser("test@test", "Poland", "Krk", "Guy",
                "Strong",
                LocalDate.of(1990, Month.APRIL, 25).toEpochDay());
        testEntityManager.persist(account);
        testEntityManager.flush();
        testEntityManager.clear();
        user = testEntityManager.find(User.class, testEntityManager.getId(account));
        testActivityType = new ActivityType(
                "test",
                Duration.ZERO,
                1000
        );
    }

    @Test
    public void shouldAddActivity() {
        // given
        Activities testActivities = new Activities(user);
        Activity activity = new Activity(testActivityType, testActivities);
        Activity activity1 = new Activity(testActivityType, testActivities);
        testEntityManager.persist(testActivities);
        testEntityManager.persist(testActivityType);
        testEntityManager.persist(activity);
        testEntityManager.persist(activity1);
        // when
        testEntityManager.flush();
        testEntityManager.clear();
        testActivities = testEntityManager.find(Activities.class, testActivities.getId());
        // then
        assertEquals( 2, testActivities.getActivities().size(),
                "List size differs.");
    }

    @Test
    public void shouldntBeAbleToModifyList() {
        // given
        Activities activities = new Activities(user);
        Activity activity = new Activity(testActivityType, activities);
        Activity activity1 = new Activity(testActivityType, activities);
        // when
        activities.addActivity(activity);
        // then
        assertThrows( UnsupportedOperationException.class, () -> activities.getActivities().add(activity1),
                "Was able to add activity.");
    }

    @Test
    public void shouldSumExperience(){
        // given
        Activities activities = new Activities(user);
        Activity activity = new Activity(testActivityType, activities);
        Activity activity1 = new Activity(testActivityType, activities);
        activity.startActivity();
        activity.finishActivity();
        activity1.startActivity();
        activity1.finishActivity();
        testEntityManager.persist(testActivityType);
        testEntityManager.persist(activities);
        testEntityManager.persist(activity);
        testEntityManager.persist(activity1);
        testEntityManager.flush();
        testEntityManager.clear();
        // when

        activities = testEntityManager.find(Activities.class, activities.getId());

        // then
        assertEquals( 2000, activities.getTotalExperience(),
                "Total experience differs from what was expected");
    }

    private Account createUser(final String emailAddress, final String country, final String city,
            final String username, final String password, final long birthDate) {
        Address address = new Address(country, city);

        Email email = new Email(emailAddress);

        LocalDate jDate = LocalDate.now();
        LocalDate bDate = Instant.ofEpochMilli(birthDate).atZone(ZoneId.systemDefault()).toLocalDate();

        Account account = new Account(email, address, username, password, jDate, bDate, 0);

        return account;
    }


}