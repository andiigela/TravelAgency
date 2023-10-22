package com.ubt.travel.travelagency.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appuser")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Firstname is required!")
    private String firstName;
    @NotBlank(message = "Lastname is required!")
    private String lastName;
    @NotBlank(message = "Username is required!")
    @Column(unique = true)
    private String username;
    @NotBlank(message = "Email is required!")
    @Column(unique = true)
    private String email;
    @Size(min = 6,message = "Password must be at least 6 characters!")
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "appuser_role",
            joinColumns = @JoinColumn(name = "appuser_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();



}
