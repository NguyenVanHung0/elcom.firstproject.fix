
package com.elcom.management_library_data.mysql.service.imp;

import com.elcom.management_library_data.convert.Mapper;
import com.elcom.management_library_data.mysql.dto.UserDto;
import com.elcom.management_library_common.exception.NoSuchElementException;
import com.elcom.management_library_data.mysql.dto.Response;
import com.elcom.management_library_data.mysql.model.User;
import com.elcom.management_library_data.mysql.repository.UserRepository;
import com.elcom.management_library_data.mysql.service.UserService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    Mapper mapper;

//    @Override
//    public UserDetails loadUserById(Long id) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            return mapper.toUserDto(user);
        }
        throw new NoSuchElementException("Khong ton tai user co ma: " + id);
    }

    @Override
    public Response createUser(UserDto userDto) {
        User user = new User();
//        if(userDto == null){
//            return new Response("Can truyen du lieu de tao tai khoan", HttpStatus.BAD_REQUEST);
//        }
        if(!userDto.getUsername().equals("")){
            User findUser = userRepository.findByUsername(userDto.getUsername());
            if(findUser == null){
                user = mapper.toUser(userDto);
                user.setCreatedDate(new Date());
                user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
                userRepository.save(user);
                return new Response("Tao tai khoan thanh cong", HttpStatus.CREATED);
            }
            else{
                return new Response("Tai khoan da ton tai", HttpStatus.BAD_REQUEST);
            }
        }
        return new Response("User Name khong duoc de trong", HttpStatus.BAD_REQUEST);
    }
    
    
}
