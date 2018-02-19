package com.todo.activities.infastructure;

import com.todo.activities.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataActivityRepository extends JpaRepository<Activity, Long> {
}
