package be.tftic.java.spring.domain.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Task {

    private long id;
    private String title;
    private String description;
    private TaskPriority priority;
    private LocalDateTime deadline;
    private LocalDateTime completedAt;

}
