package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.ResponseWrapper;
import com.cydeo.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
@Tag(name = "Project Controller", description = "Project APIs")
public class ProjectController {

    private final ProjectService projectService;


    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    @GetMapping
    @Operation(summary = "Get all projects")
    public ResponseEntity<ResponseWrapper> getAllProjects() {

        List<ProjectDTO> projectDTOList = projectService.listAllProjects();
        return ResponseEntity.ok(new ResponseWrapper("Projects are successfully retrieved", projectDTOList));
    }

    @GetMapping("{projectCode}")
    @Operation(summary = "Get project by project code")
    public ResponseEntity<ResponseWrapper> getProject(@PathVariable String projectCode) {

        ProjectDTO projectDTO = projectService.getByProjectCode(projectCode);
        return ResponseEntity.ok(new ResponseWrapper("Project is successfully retrieved", projectDTO));
    }

    @PostMapping
//    @RolesAllowed({"Admin", "Manager"})
    @Operation(summary = "Create project")
    public ResponseEntity<ResponseWrapper> createProject(@RequestBody @Valid ProjectDTO projectDTO) {

        projectService.save(projectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("Project is successfully created", 201));
    }

    @PutMapping("{projectCode}")
    @Operation(summary = "Update project")
    public ResponseEntity<ResponseWrapper> updateProject(@PathVariable String projectCode, @RequestBody ProjectDTO projectDTO) {

        projectService.update(projectCode, projectDTO);
        return ResponseEntity.ok(new ResponseWrapper("Project was successfully updated"));
    }

    @DeleteMapping("{projectCode}")
    @Operation(summary = "Delete project")
    public ResponseEntity<ResponseWrapper> deleteProject(@PathVariable String projectCode) {

        projectService.delete(projectCode);
        return ResponseEntity.ok(new ResponseWrapper("Project was successfully deleted"));
    }
    @GetMapping("/manager/project-status")
    @Operation(summary = "Get project by manager")
    public ResponseEntity<ResponseWrapper> getProjectByManager() {

        List<ProjectDTO> projectDTOList = projectService.listAllProjectDetails();

        return ResponseEntity.ok(new ResponseWrapper("Projects are successfully retrieved", projectDTOList));
    }
    @PutMapping("/manager/complete/{projectCode}")
    @Operation(summary = "Complete project")
    public ResponseEntity<ResponseWrapper> managerCompleteProject(@PathVariable String projectCode) {

        projectService.complete(projectCode);

        return ResponseEntity.ok(new ResponseWrapper("Project was successfully completed"));
    }
}
