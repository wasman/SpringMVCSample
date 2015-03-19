package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("personForm", new Person());
        return "registration";
    }

    @RequestMapping(value = "/registration/registerPerson", method = RequestMethod.POST)
    public String registerPerson(
            @ModelAttribute("personForm")
            @Valid
            Person person,
            Errors errors) {
        if (errors.hasErrors()) {
            return "/registration";
        }
        return "redirect:/helloUser/" + person.getName();
    }
}
