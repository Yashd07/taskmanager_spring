package com.yash.taskmanager.controller;

import com.yash.taskmanager.dto.CreateTaskDTO;
import com.yash.taskmanager.dto.ErrorResponseDTO;
import com.yash.taskmanager.dto.TaskResponseDTO;
import com.yash.taskmanager.dto.UpdateTaskDTO;
import com.yash.taskmanager.entities.TaskEntity;
import com.yash.taskmanager.service.NoteService;
import com.yash.taskmanager.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;



@RestController
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;
    private NoteService noteService;
    private ModelMapper modelMapper = new ModelMapper();


    public TaskController(TaskService taskService, NoteService noteService) {
        this.taskService = taskService;
        this.noteService = noteService;

    }

    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTask(){
        var tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);

    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable("id") Integer id){
        var task = taskService.getTaskById(id);
        var notes = noteService.getNotesForTask(id);

        if(task == null){
            return ResponseEntity.notFound().build();
        }
        var tasksResponse = modelMapper.map(task, TaskResponseDTO.class);
        tasksResponse.setNotes(notes);

        return ResponseEntity.ok(tasksResponse);
        
    }
    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body) throws ParseException {
        var task = taskService.addTask(body.getTitle(),body.getDescription(),body.getDeadline());
        return ResponseEntity.ok(task);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id, @RequestBody UpdateTaskDTO body) throws ParseException {
        var task = taskService.updateTask(id,body.getDescription(),body.getDeadline(),body.getCompleted());
        if(task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleErrors(Exception e){
        if(e instanceof ParseException){
            return ResponseEntity.badRequest().body(new ErrorResponseDTO( "Invalid Date format"));

        }e.printStackTrace();
        return ResponseEntity.badRequest().body(new ErrorResponseDTO( "Invalid Request"));



    }

}
