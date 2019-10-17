package com.zverikRS.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EncodePasswordController {
    @RequestMapping("/encodePassword")
    public String getJspPageAdmin() {
        return "encodePassword";
    }

    // Вспомогательный метод для определения hashCode password для последующего ручного внесения пароля в базу данных
    @RequestMapping("/encodePassword/{user}/{password}")
    public String getJspPasswordPath(@PathVariable("password") String password, @PathVariable String user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("encodePassword", new BCryptPasswordEncoder().encode(password));
        return "encodePassword";
    }
}
