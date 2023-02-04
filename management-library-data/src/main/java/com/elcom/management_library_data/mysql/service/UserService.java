package com.elcom.management_library_data.mysql.service;

import com.elcom.management_library_data.mysql.dto.Response;
import com.elcom.management_library_data.mysql.dto.UserDto;


public interface UserService {
    //UserDetails loadUserById(Long id);
    UserDto getUserById(Long id);
    Response createUser(UserDto userDto);
}
