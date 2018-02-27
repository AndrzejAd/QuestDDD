package com.todo.activities.domain.user;

import java.util.Optional;

public interface UserRepository {
    boolean doesUserExist(long userId);
    Optional<User> find(long userId);
    User save(User user);
}
