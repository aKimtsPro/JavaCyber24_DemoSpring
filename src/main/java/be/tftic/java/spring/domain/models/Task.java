package be.tftic.java.spring.domain.models;

import lombok.*;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Task {

    @EqualsAndHashCode.Include
    private long id;
    private String title;
    private String description;
    private TaskPriority priority;
    private LocalDateTime deadline;
    private LocalDateTime completedAt;

}
