package com.ubt.travel.travelagency.controllers;

import com.ubt.travel.travelagency.models.AppUser;
import com.ubt.travel.travelagency.services.AppUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthController {
    private final AppUserService appUserService;
    public AuthController(AppUserService appUserService){
        this.appUserService=appUserService;
    }
    @GetMapping("/register")
    public String getRegisterView(Model model){
        model.addAttribute("user",new AppUser());
        return "register-form";
    }
    @PostMapping("/register")
    public String registerUsers(@Valid @ModelAttribute("user") AppUser appUser, BindingResult result){
        if(appUser == null) return "redirect:/login?error=noUser";
        AppUser appUserByEmail = appUserService.findUserByEmail(appUser.getEmail());
        AppUser appUserByUsername = appUserService.findUserByUsername(appUser.getUsername());

        if(appUserByEmail != null) result.rejectValue("email","error.email","Email already exists");
        if(appUserByUsername != null) result.rejectValue("username","error.username","Username already exists");
        if(result.hasErrors()){
            return "register-form";
        }
        appUserService.createUser(appUser);
        return "redirect:/login";
    }

}
