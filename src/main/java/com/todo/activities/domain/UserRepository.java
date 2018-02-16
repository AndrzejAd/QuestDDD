package com.todo.activities.domain;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Activity, Long>{


}
