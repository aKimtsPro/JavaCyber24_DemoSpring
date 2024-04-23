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

    /**
     * Deletes all Tasks for which the 'deadline' is past and
     * either the completedAt is null (if the completed param is false)
     *        or not null (if it's true)
     *
     * @param completed true => deleting completed tasks, false => deleting non completed tasks
     */
    void deletePastTasks(boolean completed);

    /**
     * Completes a Task that has the same id as the one provided.
     * The completion date should be 'now'
     *
     * @param taskId of the targeted Task
     */
    void completeTask(long taskId);

    /**
     * Changes the priority of a task that has the same id as the one provided
     *
     * @param id of the targeted Task
     * @param priority new priority for the Task
     */
    void updatePriority(long id, TaskPriority priority);

    /**
     * Get all tasks with a deadline for this month this is not yet past.
     *
     * @return a list of future tasks
     */
    List<Task> getUrgentTasks();
}
