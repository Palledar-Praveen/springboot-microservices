package com.Cybertrons.springboot_restful_webservices.service.impl;

import com.Cybertrons.springboot_restful_webservices.dto.UserDto;
import com.Cybertrons.springboot_restful_webservices.entity.User;
import com.Cybertrons.springboot_restful_webservices.mapper.UserMapper;
import com.Cybertrons.springboot_restful_webservices.repository.UserRepository;
import com.Cybertrons.springboot_restful_webservices.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
   // @Autowired or use @AllArgsConstructor injection
    private UserRepository userRepository;

    // Injecting ModelMapper using @AllArgsConstructor injection
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

       /* //Convert UserDto object into JPA Entity
        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );*/ //User user = UserMapper.mapToUser(userDto);
        User user=modelMapper.map(userDto,User.class);
             User savedUser= userRepository.save(user);

       /*// Convert User JPA Entity into UserDto
       UserDto savedUserDto=new UserDto(
               savedUser.getId(),
               savedUser.getFirstName(),
               savedUser.getLastName(),
               savedUser.getEmail()
       );*/ //UserDto savedUserDto=UserMapper.mapToUserDto(savedUser);
        UserDto savedUserDto=modelMapper.map(savedUser,UserDto.class);
            return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user=userRepository.findById(userId).get();
        //return UserMapper.mapToUserDto(user);
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users= userRepository.findAll();
       /* return users
                    .stream()
                    .map(UserMapper::mapToUserDto)
                    .collect(Collectors.toList());*/
        return users
                    .stream()
                .map((user -> modelMapper.map(user,UserDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User excistingUser= userRepository.findById(user.getId()).get();
        excistingUser.setFirstName(user.getFirstName());
        excistingUser.setLastName(user.getLastName());
        excistingUser.setEmail(user.getEmail());
        User updatedUser=userRepository.save(excistingUser);
       // return UserMapper.mapToUserDto(updatedUser);
        return modelMapper.map(updatedUser,UserDto.class);
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
