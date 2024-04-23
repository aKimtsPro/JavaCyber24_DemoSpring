package be.tftic.java.spring.bll;

import be.tftic.java.spring.domain.models.Task;
import be.tftic.java.spring.domain.models.TaskPriority;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class TaskServiceImpl implements TaskService {

    private final Set<Task> tasks = new HashSet<>();

    @Override
    public Task getOne(long id) {
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
    public void create(Task task) {
        if( !tasks.add(task) )
            throw new RuntimeException("Task already exists");
    }

    @Override
    public void update(Task task) {
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
    }

    @Override
    public void delete(long id) {
        Task toDelete = this.getOne(id);
        this.tasks.remove(toDelete);
    }

    @Override
    public List<Task> getByPriority(TaskPriority priority) {
        return tasks.stream()
                .filter(t -> t.getPriority() == priority)
                .toList();
    }
}
