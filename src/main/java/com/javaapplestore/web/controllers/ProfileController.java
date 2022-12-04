package com.javaapplestore.web.controllers;

import com.javaapplestore.businessLayer.models.product.PostProduct;
import com.javaapplestore.businessLayer.models.user.CreateUser;
import com.javaapplestore.businessLayer.models.user.UpdateUser;
import com.javaapplestore.businessLayer.services.interfaces.UserService;
import com.javaapplestore.web.models.PostRegistrationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfileController {

    @Autowired
    UserService userService;

    @RequestMapping("/registration")
    public String getRegistrationPage(){
        return "registration";
    }

    @RequestMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @RequestMapping("/profile-edit")
    public String getProfileEditPage(Model model, @AuthenticationPrincipal User user){
        try{
            var dbUser = userService.getUserByLogin(user.getUsername());
            if (dbUser.isEmpty()) {
                return "redirect:login";
            }

            model.addAttribute("user", dbUser.get());

            return "profile-edit";

        } catch (Exception ex) {
            return "error";
        }
    }

    @PostMapping("/registration")
    public ResponseEntity registrate(@RequestBody PostRegistrationModel registrationModel){
        try{
            var checkLogin = userService.getUserByLogin(registrationModel.getLogin());
            if(!checkLogin.isEmpty())
            {
                return ResponseEntity.status(400).build();
            }
            var createUser = new CreateUser();
            createUser.setFirstName(registrationModel.getFirstName());
            createUser.setLastName(registrationModel.getLastName());
            createUser.setLogin(registrationModel.getLogin());
            createUser.setPassword(registrationModel.getPassword());
            userService.registerUser(createUser);
            return ResponseEntity.status(200).build();
        }
        catch (Exception ex){
            return ResponseEntity.status(500).build();
        }
    }

    @RequestMapping("/profile")
    public String getBasket(Model model, @AuthenticationPrincipal User user) {
        try{
            var dbUser = userService.getUserByLogin(user.getUsername());
            if (dbUser.isEmpty()) {
                return "redirect:login";
            }

            model.addAttribute("user", dbUser.get());

            return "profile";

        } catch (Exception ex) {
            return "error";
        }
    }

    @PutMapping("/profile")
    public ResponseEntity updateUser(@RequestBody UpdateUser updateUserModel) {
        try{

            userService.updateUser(updateUserModel);
            return ResponseEntity.status(200).build();
        }
        catch (Exception ex){
            return ResponseEntity.status(500).build();
        }
    }


}