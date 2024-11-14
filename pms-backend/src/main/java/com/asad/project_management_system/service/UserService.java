package com.asad.project_management_system.service;

import com.asad.project_management_system.model.AppUser;

public interface UserService {
    AppUser findUserProfileByJwt(String jwt)throws Exception;

    AppUser findUserByEmail(String email)throws Exception;

    AppUser findUserById(Long userId)throws Exception;

    AppUser updateUsersProjectSize(AppUser user, int number);
}
