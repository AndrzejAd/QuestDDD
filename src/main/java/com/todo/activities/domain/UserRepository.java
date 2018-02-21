package com.todo.activities.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository {
    boolean doesUserExist(long userId);
    Optional<User> find(long userId);
    User save(User user);
}
