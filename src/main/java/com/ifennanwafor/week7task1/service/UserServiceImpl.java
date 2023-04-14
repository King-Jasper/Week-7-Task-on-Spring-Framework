package com.ifennanwafor.week7task1.service;

import com.ifennanwafor.week7task1.dto.UserDto;
import com.ifennanwafor.week7task1.models.User;
import com.ifennanwafor.week7task1.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }
    @Override
    public UserDto saveUser(UserDto userDTO) {
        User user = new User(userDTO.getFirstName(),
                userDTO.getLastName(), userDTO.getEmail(),
                userDTO.getPassword());
        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User loginUser(UserDto userDto) {
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        User user = findByEmail(email);
        if (user != null &&
                user.checkPassword(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElse(null);
    }
}



