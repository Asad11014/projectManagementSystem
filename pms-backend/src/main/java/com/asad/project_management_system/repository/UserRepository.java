package com.asad.project_management_system.repository;

import com.asad.project_management_system.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByEmail(String email);
}
