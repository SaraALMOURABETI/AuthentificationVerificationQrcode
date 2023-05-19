


package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AuthController {




    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

  /*  @PostMapping("/inscription")
    public String submitForm(@RequestParam("NomPrenom") String nomPrenom, @RequestParam("Numero") String numero, @RequestParam("email") String email, @RequestParam("NomSociete") String nomSociete, @RequestParam("Poste") String poste, @RequestParam("Resumé") String resume, @RequestParam("site-web") String siteWeb, @RequestParam("instagram") String instagram, @RequestParam("linkedIn") String linkedIn, @RequestParam("phone") String phone, @RequestParam("instaCompany") String instaCompany, @RequestParam("facebookCompany") String facebookCompany, @RequestParam("linkedInCompany") String linkedInCompany, @RequestParam("Genre") String genre) {

        // Handle the form submission here

        return "redirect:/success"; // Redirect to success page
    }*/
    @GetMapping("/my-page")
    public String myPage(Model model) {
        // Add any necessary model attributes here
        return "my-page";
    }

    @GetMapping("index")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "login";
    }

    // handler method to handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model) {
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "login";
    }

  /*
    @PostMapping("/login")
    public String login(@ModelAttribute("user") UserDto user, BindingResult result, Model model) {
        String email = user.getEmail();
        String password = user.getPassword();
        if (result.hasErrors()) {
            return "login";
        }
        User loggedInUser = userService.findByEmailAndPassword(email, password);
        if (loggedInUser == null) {
            result.rejectValue("email", null, "Invalid email or password");
            return "login";
        }
        userService.loginUser(user);
        return "users";
    }*/
}
