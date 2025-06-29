package com.example.College.service;

import com.example.College.dto.ProjectDto;
import com.example.College.entity.Employee;
import com.example.College.entity.Projects;
import com.example.College.repository.EmployeeRepository;
import com.example.College.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectService {


    private final ModelMapper modelMapper;
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    public ProjectService(ModelMapper modelMapper, ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.modelMapper = modelMapper;
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<ProjectDto> getAllProjects(){
        return projectRepository.findAll()
                .stream()
                .map(projects -> modelMapper.map(projects, ProjectDto.class))
                .collect(Collectors.toList());
    }

    public Optional<ProjectDto> getProjectById(Long id) {
        Optional<Projects> projects = projectRepository.findById(id);
        return projects.map(projects1 -> modelMapper.map(projects,ProjectDto.class));
    }

    public ProjectDto createProject(ProjectDto projectDto) {
        return modelMapper.map(projectRepository.save(modelMapper.map(projectDto,Projects.class)),ProjectDto.class);
    }

    public boolean isEmployeePartOfProject(Employee employee,Projects project){
        return project.getMembers().contains(employee);
    }


    public ProjectDto assignProjectToEmployee(Long empId, Long projectId) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        Projects project = projectRepository.findById(projectId).orElseThrow(()->new IllegalArgumentException("Project Not found"));

        if (isEmployeePartOfProject(employee,project)){
            throw new RuntimeException("Employee Already part of project");
        }
        project.getMembers().add(employee);
        employee.getProjects().add(project);
        employeeRepository.save(employee);
        projectRepository.save(project);
        return modelMapper.map(project,ProjectDto.class);
    }
}
