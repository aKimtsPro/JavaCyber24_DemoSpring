package be.tftic.java.spring.bll.impl;

import be.tftic.java.spring.bll.TaskService;
import be.tftic.java.spring.domain.models.Task;
import be.tftic.java.spring.domain.models.TaskPriority;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class TaskServiceImpl implements TaskService {

    private final Set<Task> tasks = new HashSet<>();

    @Override
    public Task getOne(Long id) {
        return tasks.stream()
                .filter( t -> t.getId() == id )
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No task found"));
    }

    @Override
    public List<Task> getAll() {
        return List.copyOf(tasks);
    }

    @Override
    public Task create(Task task) {
        if( !tasks.add(task) )
            throw new RuntimeException("Task already exists");

        return task;
    }

    @Override
    public Task update(Task task) {
        // v1
//        this.delete(task.getId());
//        this.create(task);
        // v2
        Task toUpdate = this.getOne(task.getId());
        toUpdate.setTitle( task.getTitle() );
        toUpdate.setDescription( task.getDescription() );
        toUpdate.setPriority( task.getPriority() );
        toUpdate.setDeadline( task.getDeadline() );
        toUpdate.setCompletedAt( task.getCompletedAt() );
        return toUpdate;
    }

    @Override
    public Task delete(Long id) {
        Task toDelete = this.getOne(id);
        this.tasks.remove(toDelete);
        return toDelete;
    }

    @Override
    public List<Task> getByPriority(TaskPriority priority) {
        return tasks.stream()
                .filter(t -> t.getPriority() == priority)
                .toList();
    }


    @Override
    public void deletePastTasks(boolean completed) {
        LocalDateTime now = LocalDateTime.now();
        Collection<Task> toDelete;

        toDelete = tasks.stream()
                .filter(t -> t.getDeadline().isBefore(now))
                .filter( t ->  (!completed && t.getCompletedAt() == null) || (completed && t.getCompletedAt() != null))
                .toList();

        toDelete.forEach( tasks::remove );
    }

    @Override
    public void completeTask(long taskId) {
        Task task = this.getOne(taskId);

        if( task.getCompletedAt() != null )
            throw new RuntimeException("task already completed");

        task.setCompletedAt( LocalDateTime.now() );
    }

    @Override
    public void updatePriority(long id, TaskPriority priority) {
        Task task = this.getOne(id);
        task.setPriority( priority );
    }

    @Override
    public List<Task> getUrgentTasks() {
        LocalDateTime now = LocalDateTime.now();
        return tasks.stream()
                .filter( t -> t.getDeadline().isAfter(now) )
                .filter( t -> t.getDeadline().getYear() == now.getYear() )
                .filter( t -> t.getDeadline().getMonth() == now.getMonth() )
                .toList();
    }
}
