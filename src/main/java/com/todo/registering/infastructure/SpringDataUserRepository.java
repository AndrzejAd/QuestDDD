package com.todo.registering.infastructure;

import com.todo.registering.domain.Email;
import com.todo.registering.domain.User;
import com.todo.registering.domain.UsersRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataUserRepository extends UsersRepository, JpaRepository<User, Long>  {
    User save(User user);
    void delete(User user);
    List<User> findAll();
    Optional<User> findByEmail(Email email);

}
