package com.geekster.assignment.UserManagement.BeanMaker;

import com.geekster.assignment.UserManagement.Model.Type;
import com.geekster.assignment.UserManagement.Model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class BeanMaker {

    @Bean
    public List<User> getUsers(){

        return new ArrayList<>();
    }
}
