package com.ubt.travel.travelagency.controllers;

import com.ubt.travel.travelagency.dto.AppUserDto;
import com.ubt.travel.travelagency.models.AppUser;
import com.ubt.travel.travelagency.services.AppUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
        model.addAttribute("user",new AppUserDto());
        return "register-form";
    }
    @PostMapping("/register")
    public String registerUsers(@Valid @ModelAttribute("user") AppUserDto appUserDto, BindingResult result){
        if(appUserDto == null) return "redirect:/login?error=noUser";
        AppUser appUserByEmail = appUserService.findUserByEmail(appUserDto.getEmail());
        AppUser appUserByUsername = appUserService.findUserByUsername(appUserDto.getUsername());
        if(appUserByEmail != null) result.rejectValue("email","error.email","Email already exists");
        if(appUserByUsername != null) result.rejectValue("username","error.username","Username already exists");
        if(result.hasErrors()){
            return "register-form";
        }
        appUserService.createUser(appUserDto);
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String getLoginView(Model model){
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
        if(!(authUser instanceof AnonymousAuthenticationToken)){
            return "redirect:/dashboard";
        }
        model.addAttribute("user",new AppUser());
        return "login-form";
    }
    @GetMapping("/dashboard")
    public String getDashboardView(Model model) {
        return "dashboard";
    }


    }
