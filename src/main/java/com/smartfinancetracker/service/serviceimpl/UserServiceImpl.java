package com.smartfinancetracker.service.serviceimpl;

import com.smartfinancetracker.dao.UserDao;
import com.smartfinancetracker.models.User;
import com.smartfinancetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userRepository;

    @Autowired
    public UserServiceImpl(UserDao userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User loginUser(User user) {
       User existingUser =  userRepository.findByEmail(user.getEmail());
       if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
           return existingUser;
       }
       return null;
    }

}
