package com.asad.project_management_system.service;

import com.asad.project_management_system.config.JwtProvider;
import com.asad.project_management_system.model.AppUser;
import com.asad.project_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public AppUser findUserProfileByJwt(String jwt) throws Exception {
        String email = JwtProvider.getEmailFromToken(jwt);

        return findUserByEmail(email);
    }

    @Override
    public AppUser findUserByEmail(String email) throws Exception {
        AppUser user = userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("User not found");
        }
        return user;
    }

    @Override
    public AppUser findUserById(Long userId) throws Exception {
        Optional<AppUser> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new Exception("User not found");
        }
        return optionalUser.get();
    }

    @Override
    public AppUser updateUsersProjectSize(AppUser user, int number) {
        user.setProjectSize(user.getProjectSize() + number);

        return userRepository.save(user);
    }
}
