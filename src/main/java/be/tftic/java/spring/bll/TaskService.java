package be.tftic.java.spring.bll;

import be.tftic.java.spring.domain.models.Task;
import be.tftic.java.spring.domain.models.TaskPriority;

import java.util.List;

public interface TaskService {

    Task getOne(long id);
    List<Task> getAll();

    void create(Task task);

    void update(Task task);

    void delete(long id);

    List<Task> getByPriority(TaskPriority priority);

}
