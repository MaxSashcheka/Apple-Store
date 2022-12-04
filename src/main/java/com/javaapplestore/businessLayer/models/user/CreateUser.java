package com.javaapplestore.businessLayer.models.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUser {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
}
