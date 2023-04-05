package com.user.userapp.service;

import com.user.userapp.domain.Photo;
import com.user.userapp.domain.User;
import com.user.userapp.domain.enums.Status;
import com.user.userapp.repository.UserRepository;
import com.user.userapp.web.errors.UsernameAlreadyUsedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * Service class for managing users.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PhotoService photoService;

    public UserService(UserRepository userRepository, PhotoService photoService) {
        this.userRepository = userRepository;
        this.photoService = photoService;
    }

    /**
     * @return a list of all users
     */
    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    /**
     * Create user with information and return the user.
     *
     * @param user user to create.
     */
    @Transactional
    public void createUser(User user) throws IOException {
        Photo userPhoto = photoService.createAvatar(user.getPhoto());
        if(userRepository.findByUserName(user.getUserName()).isPresent())
            throw new UsernameAlreadyUsedException("Username already used!");
        User newUser = new User(
                user.getFirstName(),
                user.getLastName(),
                user.getUserName(),
                Status.Inactive,
                user.getLocation(),
                user.getPhone(),
                userPhoto
        );
        userRepository.save(newUser);
    }

    /**
     * Delete user if the user exists in database.
     *
     * @param userId to find user by id to delete.
     */
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresent(userRepository::delete);
    }
}