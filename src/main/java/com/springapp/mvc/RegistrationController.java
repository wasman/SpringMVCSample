package com.springapp.mvc;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
public class RegistrationController {

    private static final String EMPTY_STRING = "";

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String register(
            Model model,
            @CookieValue(value = "uuid", defaultValue = EMPTY_STRING) String uuid,
            HttpServletResponse response) {
        if (uuid.isEmpty()) {
            response.addCookie(new Cookie("uuid", UUID.randomUUID().toString()));
        }
        model.addAttribute("personForm", new Person());
        return "registration";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, "birthDate", new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/registration/registerPerson", method = RequestMethod.POST)
    public String registerPerson(
            @ModelAttribute("personForm")
            @Valid
            Person person,
            Errors errors,
            @CookieValue(value = "uuid") String uuid) throws PersonRegisterException {
        if (errors.hasErrors()) {
            return "/registration";
        }
        if (person.getBirthDate().after(new Date())) {
            throw new PersonRegisterException(uuid);
        }
        person.setUuid(UUID.fromString(uuid));

        System.out.println("User registered " + person);
        return "redirect:/helloUser/" + person.getName();
    }

//    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Date is wrong")
    @ExceptionHandler(PersonRegisterException.class)
    public ModelAndView handlePersonRegisterException(PersonRegisterException ex) {

        ModelAndView modelMap = new ModelAndView();
        System.out.println(ex.getUuid());
        modelMap.addObject("uuid",ex.getUuid());
        modelMap.setViewName("registration_error");
        return modelMap;
    }
}
