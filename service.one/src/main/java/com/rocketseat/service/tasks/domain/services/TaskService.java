package com.rocketseat.service.tasks.domain.services;

import com.rocketseat.service.tasks.adapter.in.entities.TasksEntity;
import com.rocketseat.service.tasks.adapter.in.repositories.TasksRepository;
import com.rocketseat.service.tasks.adapter.in.requests.NotificationRequest;
import com.rocketseat.service.tasks.adapter.out.client.NotificationClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    private final TasksRepository repository;
    private final NotificationClient client;


    public TaskService(TasksRepository repository, NotificationClient client) {
        this.repository = repository;
        this.client = client;
    }

    public void sendNotificationForDueTasks() {
        LocalDateTime deadline = LocalDateTime.now().plusDays(1);
        List<TasksEntity> tasks = repository.findTasksDueWithinDeadline(deadline);

        for (TasksEntity task : tasks) {
            NotificationRequest request = new NotificationRequest("Sua tarefa: " + task.getTitle() +
                    " esta prestes a vencer ", task.getEmail());
            client.sendNotification(request);
            task.setNotified(true);
            repository.save(task);
        }
    }

}
