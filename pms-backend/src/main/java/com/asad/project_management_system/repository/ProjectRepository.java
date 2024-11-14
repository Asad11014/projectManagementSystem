package com.asad.project_management_system.repository;

import com.asad.project_management_system.model.Project;
import com.asad.project_management_system.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

  // List<Project>findByOwner(User user);

   List<Project> findByNameContainingAndTeamContains(String partialName, AppUser user);

//   @Query("SELECT p From Project p join p.team t where t=:user")
//   List<Project> findProjectByTeam(@Param("user") User user);

   List<Project> findByTeamContainingOrOwner(AppUser user, AppUser owner);
}
