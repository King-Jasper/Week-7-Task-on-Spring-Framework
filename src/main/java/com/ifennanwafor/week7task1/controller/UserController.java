package com.ifennanwafor.week7task1.controller;

import com.ifennanwafor.week7task1.dto.UserDto;
import com.ifennanwafor.week7task1.models.User;
import com.ifennanwafor.week7task1.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class UserController {

    private final IUserService userService;
    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDTO", new UserDto());
        return "register";
    }
    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute("userDTO") UserDto userDto,
                                          RedirectAttributes redirectAttributes) {
        if (!userDto.getPassword()
                .equals(userDto.getRepeatPassword())) {
            redirectAttributes.addFlashAttribute("passwordError", "Incorrect password");
            return "redirect:/register";
        }
        userService.saveUser(userDto);
        return "login";
    }
    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("userDTO", new UserDto());
        return "login";
    }
    @PostMapping("/login")
    public ModelAndView loginUser(@ModelAttribute("userDTO") UserDto userDto,
                            HttpServletRequest request,
                            RedirectAttributes redirectAttributes) {
        User user = userService.loginUser(userDto);
        if (userDto.getEmail().equals("admin@shop.com") && userDto.getPassword().equals("admin")) {
            return new ModelAndView("redirect:/dashboard");
        } else if (user == null) {
            System.out.println("User is null");
            redirectAttributes.addFlashAttribute("loginError", "Invalid email or password...");
            return new ModelAndView("redirect:/");
        } else {
            HttpSession session = request.getSession();
            Long id = user.getUserID();
            session.setAttribute("userID", id);
            return new ModelAndView("redirect:/index");
        }
    }
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
