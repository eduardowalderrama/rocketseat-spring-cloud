package com.rocketseat.service.tasks.adapter.in.requests;

import java.time.LocalDateTime;

public record TaskRequest(String title, String email, LocalDateTime dueDate, Boolean notified) {
}
