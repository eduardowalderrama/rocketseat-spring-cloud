package com.rocketseat.service.tasks.adapter.in.controllers;

import com.rocketseat.service.tasks.adapter.in.entities.TasksEntity;
import com.rocketseat.service.tasks.adapter.in.repositories.TasksRepository;
import com.rocketseat.service.tasks.adapter.in.requests.TaskRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    private final TasksRepository tasksRepository;

    public TasksController(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @PostMapping
    public ResponseEntity<TasksEntity> createTask(@RequestBody TaskRequest request) {
        TasksEntity tasksEntity = new TasksEntity(request);
        TasksEntity save = this.tasksRepository.save(tasksEntity);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }
}
