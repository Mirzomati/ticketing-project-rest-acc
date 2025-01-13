package com.cydeo.controller;

import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
@Tag(name = "Task Controller", description = "Task APIs")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @Operation(summary = "Get all tasks")
    public ResponseEntity<ResponseWrapper> getAllTasks() {

        List<TaskDTO> taskDTOList = taskService.listAllTasks();

        return ResponseEntity.ok(new ResponseWrapper("Tasks are successfully retrieved", taskDTOList));
    }
    @GetMapping("/{taskCode}")
    @Operation(summary = "Get task by task code")
    public ResponseEntity<ResponseWrapper> getTaskByTaskCode(@PathVariable String taskCode) {

        TaskDTO taskDTO = taskService.findByTaskCode(taskCode);

        return ResponseEntity.ok(new ResponseWrapper("Task is successfully retrieved", taskDTO));
    }

    @PostMapping
    @Operation(summary = "Create task")
    public ResponseEntity<ResponseWrapper> createTask(@RequestBody @Valid TaskDTO taskDTO) {

        taskService.save(taskDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("Task was successfully created", 201));
    }

    @PutMapping("/{taskCode}")
    @Operation(summary = "Update task")
    public ResponseEntity<ResponseWrapper> updateTask(@PathVariable String taskCode, @RequestBody TaskDTO taskDTO) {

        taskService.update(taskCode,taskDTO);

        return ResponseEntity.ok(new ResponseWrapper("Task was successfully updated"));
    }
    @DeleteMapping("/{taskCode}")
    @Operation(summary = "Delete task")
    public ResponseEntity<ResponseWrapper> deleteTask(@PathVariable String taskCode) {

        taskService.delete(taskCode);

        return ResponseEntity.ok(new ResponseWrapper("Task was successfully deleted"));
    }

    @GetMapping("/employee/pending-tasks")
    @Operation(summary = "Get pending tasks")
    public ResponseEntity<ResponseWrapper> employeePendingTasks() {

        List<TaskDTO> taskDTOList = taskService.listAllTasksByStatusIsNot(Status.COMPLETE);

        return ResponseEntity.ok(new ResponseWrapper("Tasks are successfully retrieved", taskDTOList));

    }
    @GetMapping("/employee/archive")
    @Operation(summary = "Get archived tasks")
    public ResponseEntity<ResponseWrapper> employeeArchiveTasks() {

        List<TaskDTO> taskDTOList = taskService.listAllTasksByStatus(Status.COMPLETE);

        return ResponseEntity.ok(new ResponseWrapper("Tasks are successfully retrieved", taskDTOList));

    }

    @PutMapping("/employee/update/{taskCode}")
    @Operation(summary = "Update task")
    public ResponseEntity<ResponseWrapper> employeeUpdateTask(@PathVariable String taskCode, @RequestBody TaskDTO taskDTO) {

        taskService.update(taskCode,taskDTO);

        return ResponseEntity.ok(new ResponseWrapper("Task was successfully updated"));

    }


















}
