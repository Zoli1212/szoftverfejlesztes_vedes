package com.szoftverfejlesztes.kanban.controller;

import com.szoftverfejlesztes.kanban.entity.Task;
import com.szoftverfejlesztes.kanban.exception.ResourceNotFoundException;
import com.szoftverfejlesztes.kanban.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins ="http://localhost:3000")
public class TaskController {

    @Autowired
    private TaskRepository repo;

    @GetMapping("/tasks")
    public ResponseEntity<?> getAllTasks(){
        return ResponseEntity.ok(repo.findAll());
    }

    @PostMapping("/tasks")
    public ResponseEntity<?> createTas(@RequestBody Task task){
        return new ResponseEntity<>(repo.save(task), HttpStatus.CREATED);
    }

    @GetMapping("tasks/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id){


        Task task = repo.findById(id).orElseThrow(()->new ResourceNotFoundException("This task doesnt exist in Db "+id));
        return new ResponseEntity<Task>(task, HttpStatus.OK);


    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,@RequestBody Task taskDetails){

        Task task = repo.findById(id).orElseThrow(()->new ResourceNotFoundException("This task doesnt exist in Db "+id));

        task.setLanguage(taskDetails.getLanguage());
        task.setProject(taskDetails.getProject());
        task.setDeveloper(taskDetails.getDeveloper());
        repo.save(task);
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
        Task task = repo.findById(id).orElseThrow(()->new ResourceNotFoundException("This task doesnt exist in Db "+id));

        repo.delete(task);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
