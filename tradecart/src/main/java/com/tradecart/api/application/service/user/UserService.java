package com.tradecart.api.application.service.user;

import com.tradecart.api.domain.model.User;
import com.tradecart.api.domain.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    private UserService(UserRepository repository){
        this.repository = repository;
    }

    public User updateUser(long id, User user){
        User existingUser = repository.findById(id).orElse(null);
        if (existingUser == null) {
            return null;
        }

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setLogin(user.getLogin());
        existingUser.setPassword(user.getPassword());
        existingUser.setLocation(user.getLocation());

        return repository.save(existingUser);
    }

    public User getUserById(long id){
        return repository.findById(id).orElse(null);
    }
}
