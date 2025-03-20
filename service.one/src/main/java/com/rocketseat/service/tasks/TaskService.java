package com.rocketseat.service.tasks;

import java.util.List;

public class TaskService {
    private final TasksRepository repository;
    private final NotificationClient client;


    public TaskService(TasksRepository repository, NotificationClient client) {
        this.repository = repository;
        this.client = client;
    }

    public void sendNotificationForDueTasks() {
        List<TasksEntity> tasks = repository.findDueTasks();
        for (TasksEntity task : tasks) {
            NotificationRequest request = new NotificationRequest("Sua tarefa: " + task.getTitle() +
                    " esta prestes a vencer ", task.getEmail());
            client.sendNotification(request);
            task.setNotified(true);
        }
    }

}
