package be.tftic.java.spring.api.controllers;

import be.tftic.java.spring.bll.TaskService;
import be.tftic.java.spring.domain.models.Task;
import be.tftic.java.spring.domain.models.TaskPriority;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // GET - http://localhost:8080/tasks
    @GetMapping()
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> body = taskService.getAll();
        return ResponseEntity.ok(body);
    }

    // GET - http://localhost:8080/tasks/1
    @GetMapping("/{id:\\d+}")
    public ResponseEntity<Task> getOneTask(@PathVariable long id){
        Task body = taskService.getOne(id);
        return ResponseEntity.ok(body);
    }

    // POST - http://localhost:8080/tasks
    // POST - http://localhost:8080/tasks/add
    @PostMapping({ "", "/add" })
    public ResponseEntity<Void> createTask(@RequestBody Task toCreate){
        taskService.create(toCreate);
        URI uri = URI.create("/tasks/"+toCreate.getId());
        return ResponseEntity.created(uri) // 2O1
                .build();
    }


    // GET - http://localhost:8080/tasks?priority=HIGH
    @GetMapping(params = "priority")
    public ResponseEntity<List<Task>> getByPriority(@RequestParam TaskPriority priority){
        List<Task> body = taskService.getByPriority(priority);
        return ResponseEntity.ok(body);
    }

    // DELETE - http://localhost:8080/tasks/1
    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        taskService.delete(id);
        return ResponseEntity.noContent() // 204
                .build();
    }

    // PUT - http://localhost:8080/tasks
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Task task){
        taskService.update(task);
        return ResponseEntity.noContent() // 204
                .build();
    }

}
