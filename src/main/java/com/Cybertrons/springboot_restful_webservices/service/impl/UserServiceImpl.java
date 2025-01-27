package com.Cybertrons.springboot_restful_webservices.service.impl;

import com.Cybertrons.springboot_restful_webservices.dto.UserDto;
import com.Cybertrons.springboot_restful_webservices.entity.User;
import com.Cybertrons.springboot_restful_webservices.mapper.UserMapper;
import com.Cybertrons.springboot_restful_webservices.repository.UserRepository;
import com.Cybertrons.springboot_restful_webservices.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
   // @Autowired or use @AllArgsConstructor injection
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

       /* //Convert UserDto object into JPA Entity
        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );*/ User user = UserMapper.mapToUser(userDto);
             User savedUser= userRepository.save(user);

       /*// Convert User JPA Entity into UserDto
       UserDto savedUserDto=new UserDto(
               savedUser.getId(),
               savedUser.getFirstName(),
               savedUser.getLastName(),
               savedUser.getEmail()
       );*/ UserDto savedUserDto=UserMapper.mapToUserDto(savedUser);
            return savedUserDto;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        User excistingUser= userRepository.findById(user.getId()).get();
        excistingUser.setFirstName(user.getFirstName());
        excistingUser.setLastName(user.getLastName());
        excistingUser.setEmail(user.getEmail());
        User updatedUser=userRepository.save(excistingUser);
        return updatedUser;
    }

   /* @Override
    public List<User> updateUsers(List<User> users) {
        return userRepository.saveAll(users);
    }*/

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
