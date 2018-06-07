package com.cooksys.controller;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.dto.ProjectDto;
import com.cooksys.service.ProjectService;

@RestController
@RequestMapping("project")
public class ProjectController {
	
	private ProjectService projectService;

	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@GetMapping
	public List<ProjectDto> getAll() {
		return projectService.getAll();
	}
	
	@GetMapping("{id}")
	public ProjectDto get(@PathVariable Long id) {
		return projectService.get(id);
	}
	
	@GetMapping("overdue")
	public List<ProjectDto> getOverdue() {
		return projectService.getOverdue();
	}
	
	@GetMapping("manager/{id}")
	public List<ProjectDto> getByManagerId(@PathVariable Long id) {
		return projectService.getByManagerId(id);
	}
	
	@GetMapping("manager/{id}/overdue")
	public List<ProjectDto> getOverdueByManagerId(@PathVariable Long id) {
		return projectService.getOverdueByManagerId(id);
	}
	
	@PostMapping
	public Long post(@RequestBody ProjectDto projectDto) {
		Long id = projectService.post(projectDto);
		return id;
	}
	
	@PutMapping("{id}")
	public void put(@PathVariable Long id, @RequestBody ProjectDto projectDto) {
		projectService.put(id, projectDto);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		projectService.delete(id);
	}
}
