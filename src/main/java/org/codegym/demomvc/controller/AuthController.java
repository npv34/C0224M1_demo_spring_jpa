package org.codegym.demomvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/login")
    public String showLoginForm(@CookieValue(value = "username", defaultValue = "") String username,
                                Model model, HttpSession httpSession) {
        String messageError = (String) httpSession.getAttribute("errorLogin");
        model.addAttribute("username", username);
        model.addAttribute("errorLogin", messageError);
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession httpSession) {
        if (username.equals("admin") && password.equals("1234")) {
            // tao session
            httpSession.setAttribute("userLogin" , username);
            return "redirect:/groups";
        }
        httpSession.setAttribute("errorLogin" , "Account not exist!");
        return "redirect:/auth/login";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("birthday") String birthday,
                               HttpServletResponse response) {
        // Handle registration logic
        // save database

        // Tao cookie
        Cookie cookie = new Cookie("username", username);
        // thoi gian cookie ton tai
        cookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
        cookie.setHttpOnly(true);
        // set cookie vao response
        response.addCookie(cookie);


        return "redirect:/auth/login";
    }
}
