package eSchoolProject.controller;

import eSchoolProject.model.Authorization;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by JoKeR on 24.12.2016.
 */
@Controller
public class AuthorizationController {

    @GetMapping("/login")
    public String authorizationForm(Model model) {
        model.addAttribute("authorization", new Authorization());
        return "login";
    }
}