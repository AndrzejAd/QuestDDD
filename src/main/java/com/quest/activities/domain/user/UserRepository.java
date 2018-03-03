package com.quest.activities.domain.user;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {
    boolean doesUserExist(long userId);
    Optional<User> find(long userId);
    User save(User user);
    Collection<User> getAllUsers();
}
