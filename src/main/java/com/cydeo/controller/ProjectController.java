package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.ResponseWrapper;
import com.cydeo.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;


    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllProjects() {

        List<ProjectDTO> projectDTOList = projectService.listAllProjects();
        return ResponseEntity.ok(new ResponseWrapper("Projects are successfully retrieved", projectDTOList));
    }

    @GetMapping("{projectCode}")
    public ResponseEntity<ResponseWrapper> getProject(@PathVariable String projectCode) {

        ProjectDTO projectDTO = projectService.getByProjectCode(projectCode);
        return ResponseEntity.ok(new ResponseWrapper("Project is successfully retrieved", projectDTO));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createProject(@RequestBody ProjectDTO projectDTO) {

        projectService.save(projectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("Project is successfully created", 201));
    }

    @PutMapping("{projectCode}")
    public ResponseEntity<ResponseWrapper> updateProject(@PathVariable String projectCode, @RequestBody ProjectDTO projectDTO) {

        projectService.update(projectCode, projectDTO);
        return ResponseEntity.ok(new ResponseWrapper("Project was successfully updated"));
    }

    @DeleteMapping("{projectCode}")
    public ResponseEntity<ResponseWrapper> deleteProject(@PathVariable String projectCode) {

        projectService.delete(projectCode);
        return ResponseEntity.ok(new ResponseWrapper("Project was successfully deleted"));
    }
}
