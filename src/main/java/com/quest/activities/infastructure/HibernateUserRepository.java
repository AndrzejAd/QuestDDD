package com.quest.activities.infastructure;
import com.quest.activities.domain.user.User;
import com.quest.activities.domain.user.UserRepository;
import com.quest.accounts.saving.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Repository
@Transactional
public class HibernateUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public HibernateUserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean doesUserExist(long userId) {
        return !Objects.isNull( entityManager.find(Account.class, userId) );
    }

    @Override
    public Optional<User> find(long userId) {
        return Optional.ofNullable(entityManager.find(User.class, userId));
    }

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public Collection<User> getAllUsers() {
        return entityManager.createQuery("select u from User u").getResultList();
    }
}
