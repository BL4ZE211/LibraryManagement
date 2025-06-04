package com.LibraryManager.Library.Service;

import com.LibraryManager.Library.Entity.Users;
import com.LibraryManager.Library.Repository.UserRepository;
import com.LibraryManager.Library.payload.UserRequestDto;
import com.LibraryManager.Library.payload.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponseDto mapToResponseDto(Users users){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(users.getId());
        userResponseDto.setUsername(users.getName());

        return userResponseDto;
    }

    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        Users users = new Users();
        users.setEmail(userRequestDto.getEmail());
        users.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        if(userRequestDto.getRole()!=null){
            users.setRole(userRequestDto.getRole());
        }else{
            users.setRole("User");
        }
        users.setName(userRequestDto.getName());
        userRepository.save(users);

        return mapToResponseDto(users);
    }
}
