package com.asad.project_management_system.service;

import com.asad.project_management_system.model.Chat;
import com.asad.project_management_system.model.Project;
import com.asad.project_management_system.model.AppUser;

import java.util.List;

public interface ProjectService {

    Project createProject(Project project, AppUser user)throws Exception;

    List<Project> getProjectByTeam(AppUser user, String category, String tag)throws Exception;

    Project getProjectById(Long projectId)throws Exception;

    void deleteProject(Long projectId, Long UserId)throws Exception;

    Project updateProject(Project updatedProject, Long id)throws Exception;

    void addUserToProject(Long projectId, Long userId)throws Exception;

    void removeUserFromProject(Long projectId, Long userId)throws Exception;

    Chat getChatByProjectId(Long projectId)throws Exception;

    List<Project> searchProjects(String keyword, AppUser user)throws Exception;
}
