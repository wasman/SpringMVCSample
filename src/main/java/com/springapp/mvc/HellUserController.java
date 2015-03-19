package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/helloUser")
public class HellUserController {

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public String printHelloForUser(@PathVariable String userId, Model model) {
        model.addAttribute("message", "Hello " + userId + " !");
        return "helloUser";
    }
}