package com.rocketseat.service.tasks;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasksRepository extends JpaRepository<TasksEntity, Long> {

    List<TasksEntity> findDueTasks();
}
