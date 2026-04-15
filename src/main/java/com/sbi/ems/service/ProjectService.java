package com.sbi.ems.service;

import com.sbi.ems.dto.employee.EmployeeResponse;
import com.sbi.ems.dto.project.AssignEmployeeRequest;
import com.sbi.ems.dto.project.ProjectRequest;
import com.sbi.ems.dto.project.ProjectResponse;
import com.sbi.ems.model.Project.ProjectStatus;
import java.util.List;

public interface ProjectService {
    List<ProjectResponse>    getAllProjects(ProjectStatus status);
    ProjectResponse          getProjectById(Long id);
    ProjectResponse          createProject(ProjectRequest request);
    ProjectResponse          updateProject(Long id, ProjectRequest request);
    void                     deleteProject(Long id);
    void                     assignEmployee(Long projectId, AssignEmployeeRequest req);
    void                     removeEmployee(Long projectId, Long employeeId);
    List<EmployeeResponse>   getProjectEmployees(Long projectId);
    List<ProjectResponse>    getEmployeeProjects(Long employeeId);
}
