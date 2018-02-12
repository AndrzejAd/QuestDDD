package com.todo.registering.saving.domain;

import java.util.Collection;
import java.util.Optional;

public interface UsersRepository{
    User save(User user);
    void delete(User user);
    Collection<User> findAll();
    Optional<User> findByEmail(Email email);
}
