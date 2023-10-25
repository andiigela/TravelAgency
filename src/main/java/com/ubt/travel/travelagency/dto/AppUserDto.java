package com.ubt.travel.travelagency.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
public class AppUserDto {
    private Long id;
    @NotBlank(message = "Firstname is required!")
    private String firstName;
    @NotBlank(message = "Lastname is required!")
    private String lastName;
    @NotBlank(message = "Username is required!")
    private String username;
    @NotBlank(message = "Email is required!")
    private String email;
    @Size(min = 6,message = "Password must be at least 6 characters!")
    private String password;
}
