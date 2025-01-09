package com.cydeo.service;

import com.cydeo.dto.UserDTO;

public interface KeycloakService {

    String getLoggedInUserName();

    void userCreate(UserDTO userDTO);

    void userUpdate(UserDTO userDTO);

    void userDelete(String userName);

}
