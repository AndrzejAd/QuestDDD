package com.todo.activities.infastructure;
import com.todo.activities.domain.User;
import com.todo.activities.domain.UserRepository;
import com.todo.registering.saving.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}
