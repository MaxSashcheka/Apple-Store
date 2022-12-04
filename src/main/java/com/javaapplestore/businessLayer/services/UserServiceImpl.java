package com.javaapplestore.businessLayer.services;

import com.javaapplestore.businessLayer.models.user.CreateUser;
import com.javaapplestore.businessLayer.models.user.GetUser;
import com.javaapplestore.businessLayer.models.user.UpdateUser;
import com.javaapplestore.businessLayer.repositories.UserRepository;
import com.javaapplestore.businessLayer.repositories.UserTypeRepository;
import com.javaapplestore.businessLayer.services.interfaces.UserService;
import com.javaapplestore.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserTypeRepository userTypeRepository;

    public Optional<GetUser> getUserByLogin(String login) {
        var user = userRepository.findByLogin(login);
        if (user.isEmpty()) {
            return Optional.empty();
        }

        var userEntity = user.get();
        var getUser = new GetUser();
        getUser.setId(userEntity.getId());
        getUser.setFirstName(userEntity.getFirstName());
        getUser.setLastName(userEntity.getLastName());
        getUser.setLogin(userEntity.getLogin());
        getUser.setPassword(userEntity.getPassword());
        return Optional.of(getUser);
    }

    public void registerUser(CreateUser createUser) throws Exception {
        var user = new UserEntity();
        user.setFirstName(createUser.getFirstName());
        user.setLastName(createUser.getLastName());
        user.setLogin(createUser.getLogin());
        user.setPassword(new BCryptPasswordEncoder().encode(createUser.getPassword()));

        var existType = userTypeRepository.findByType("employee");
        if(existType.isEmpty())
        {
            throw new Exception();
        }

        user.setUserType(existType.get());
        userRepository.save(user);
    }

    public void updateUser(UpdateUser updateUserModel) throws Exception {
        var existUser = userRepository.findById(updateUserModel.getId());
        if(existUser.isEmpty())
        {
            throw new Exception();
        }

        existUser.get().setFirstName(updateUserModel.getFirstName());
        existUser.get().setLastName(updateUserModel.getLastName());
        userRepository.save(existUser.get());

    }
}
