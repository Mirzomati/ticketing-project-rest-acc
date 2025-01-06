package com.cydeo.controller;

import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.TaskDTO;
import com.cydeo.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllTasks() {

        List<TaskDTO> taskDTOList = taskService.listAllTasks();

        return ResponseEntity.ok(new ResponseWrapper("Tasks are successfully retrieved", taskDTOList));
    }
    @GetMapping("/{taskCode}")
    public ResponseEntity<ResponseWrapper> getTaskByTaskCode(@PathVariable String taskCode) {

        TaskDTO taskDTO = taskService.findByTaskCode(taskCode);

        return ResponseEntity.ok(new ResponseWrapper("Task is successfully retrieved", taskDTO));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createTask(@RequestBody TaskDTO taskDTO) {

        taskService.save(taskDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("Task was successfully created", 201));
    }

    @PutMapping("/{taskCode}")
    public ResponseEntity<ResponseWrapper> updateTask(@PathVariable String taskCode, @RequestBody TaskDTO taskDTO) {

        taskService.update(taskCode,taskDTO);

        return ResponseEntity.ok(new ResponseWrapper("Task was successfully updated"));
    }


















}
