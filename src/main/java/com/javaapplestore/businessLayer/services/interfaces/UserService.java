package com.javaapplestore.businessLayer.services.interfaces;

import com.javaapplestore.businessLayer.models.user.CreateUser;
import com.javaapplestore.businessLayer.models.user.GetUser;
import com.javaapplestore.businessLayer.models.user.UpdateUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserService {
    Optional<GetUser> getUserByLogin(String login);
    void registerUser(CreateUser createUser) throws Exception;

    public void updateUser(UpdateUser updateUserModel) throws Exception;
}
