package com.todo.activities.infastructure;

import com.todo.activities.domain.Activities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataActivitiesRepository extends JpaRepository<Activities, Long> {
}
