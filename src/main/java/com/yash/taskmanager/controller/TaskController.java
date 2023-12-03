package com.yash.taskmanager.controller;

import com.yash.taskmanager.dto.CreateTaskDTO;
import com.yash.taskmanager.entities.TaskEntity;
import com.yash.taskmanager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTask(){
        var tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);

    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable("id") Integer id){
        var task = taskService.getTaskById(id);
        if(task == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
        
    }
    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body){
        var task = taskService.addTask(body.getTitle(),body.getDescription(),body.getDeadline());
        return ResponseEntity.ok(task);

    }

}
