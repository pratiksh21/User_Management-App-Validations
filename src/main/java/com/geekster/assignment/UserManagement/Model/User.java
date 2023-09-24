package com.geekster.assignment.UserManagement.Model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NotNull
    private Integer userId;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String userName;

    private Type type;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date of birth must be in yyyy-mm-dd format")
    private String dateOfBirth;

    @Email(message = "Invalid email format. Please enter a valid Email!")
    private String userEmail;

    @Size(min = 12,max = 12)
    @Pattern( regexp = "91[0-9]{10}$",message = "Enter a Valid Phone Number,Phone number must start with 91 and be followed by 10 digits")
    private  String userContactNo;

    @NotNull(message = "Date cannot be null")
    @Past(message = "Date must be in the past")
    private LocalDate date = LocalDate.now();

    @NotNull(message = "Time cannot be null")
    private LocalTime  time =  LocalTime.now();

}
/*
    UserId
	UserName
	userType
	DateOfBirth
	Email
	Phone Number
	Date
	Time
	*/
