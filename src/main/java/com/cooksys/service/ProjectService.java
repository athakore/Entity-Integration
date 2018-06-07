package com.cooksys.service;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import com.cooksys.dto.ProjectDto;
import com.cooksys.entity.Project;
import com.cooksys.exception.ReferencedEntityNotFoundException;
import com.cooksys.mapper.ProjectMapper;
import com.cooksys.repository.ProjectRepository;

@Service
public class ProjectService {
	
	private ProjectRepository repo;
	private ProjectMapper mapper;
	
	public ProjectService(ProjectRepository repo, ProjectMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}

	public List<ProjectDto> getAll() {
		return repo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
	}

	public Long post(ProjectDto projectDto) {
		projectDto.setId(null);
		return repo.save(mapper.toEntity(projectDto)).getId();
	}

	public ProjectDto get(Long id) {
		mustExist(id);
		return mapper.toDto(repo.getOne(id));
	}

	private void mustExist(Long id) {
		if(!has(id))
			throw new ReferencedEntityNotFoundException(Project.class, id);
	}

	public boolean has(Long id) {
		return repo.exists(id);
	}

	public void put(Long id, ProjectDto projectDto) {
		mustExist(id);
		projectDto.setId(id);
		repo.save(mapper.toEntity(projectDto));
	}

	public void delete(Long id) {
		mustExist(id);
		repo.delete(id);
	}

	public List<ProjectDto> getOverdue() {
		return repo.findByDueDateLessThan(new Date()).stream().map(mapper::toDto).collect(Collectors.toList());
	}
	
	public List<ProjectDto> getByManagerId(Long id) {
		mustExist(id);
		return repo.findByManagerId(id).stream().map(mapper::toDto).collect(Collectors.toList());
	}
	
	public List<ProjectDto> getOverdueByManagerId(Long id) {
		mustExist(id);
		return repo.findByManagerIdAndDueDateLessThan(id, new Date()).stream().map(mapper::toDto).collect(Collectors.toList());
	}
	
	
}
