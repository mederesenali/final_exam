package com.example.final_exam.controller;


import com.example.final_exam.exception.CustomerAlreadyRegisteredException;
import com.example.final_exam.model.User;
import com.example.final_exam.repository.PlaceRepository;
import com.example.final_exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class LoginController {
    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    UserService userService;
    @GetMapping("/")
    public String firtsPage(Model model){
        model.addAttribute("places",placeRepository.findAll());
        return "home";
    }
    @GetMapping("/home")
    public String home(Model model, Principal principal){
        User user=userService.findByEmail(principal.getName());
         model.addAttribute("user", user);
         model.addAttribute("places",placeRepository.findAll());
        return "userPage";
    }
    @GetMapping("/login")
    public String loignPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model){
        model.addAttribute("error", error);

        return "login";
    }
    @GetMapping("/registration")
    public String register(){
        return "register";
    }
    @PostMapping("/registration")
    public String registerSubmit(@Valid User user, BindingResult bindingResult,
                                 RedirectAttributes attributes  ) throws CustomerAlreadyRegisteredException {
        attributes.addFlashAttribute("dto", user);

        if (bindingResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", bindingResult.getFieldErrors());
            return "redirect:/registration";
        }
        userService.register(user);
        return "redirect:/login";
    }
    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        String user = principal.getName();
        model.addAttribute("user", userService.findByEmail(user));
        return "profile";
    }


}
