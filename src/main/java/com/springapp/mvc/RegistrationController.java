package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("registration")
public class RegistrationController {

    @RequestMapping(method = RequestMethod.GET)
    public String register(Model model){
        return "registration";
    }

    @RequestMapping(value = "registerPerson", method = RequestMethod.POST)
    public String registerPerson(@ModelAttribute Person person, ModelMap model) {
        System.out.println(" Person " + (person != null ? person : "Unknown"));
        return "registration";
    }

}
