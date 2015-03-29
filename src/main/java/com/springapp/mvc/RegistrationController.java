package com.springapp.mvc;

import com.springapp.mvc.orm.Person;
import com.springapp.mvc.orm.PersonService;
import com.springapp.mvc.orm.PersonSession;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class RegistrationController {

    private static final String EMPTY_STRING = "";

    @Autowired
    private PersonService service;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String register(
            Model model,
            @CookieValue(value = "uuid", defaultValue = EMPTY_STRING) String uuid,
            HttpServletResponse response) {
        if (uuid.isEmpty()) {
            response.addCookie(new Cookie("uuid", UUID.randomUUID().toString()));
        }
        model.addAttribute("personForm", new PersonForMvc());
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
            @CookieValue(value = "uuid") String uuid,
            HttpServletRequest request
    ) throws PersonRegisterException {
        if (errors.hasErrors()) {
            return "/registration";
        }
        if (person.getBirthDate().after(new Date())) {
            throw new PersonRegisterException(uuid);
        }

        PersonSession personSession = new PersonSession(uuid);
        String country = request.getLocale().getDisplayCountry();
        person.setCountry(country);
        personSession.setPerson(person);
        service.save(personSession);

        System.out.println("User registered " + person);
        return "redirect:/helloUser/" + person.getName();
    }

    @RequestMapping(value = "/registration/registerPersonAsync", method = RequestMethod.POST)
    public WebAsyncTask<String> registerPersonAsync(
            @ModelAttribute("personForm")
            @Valid
            final PersonForMvc personForMvc,
            final Errors errors,
            @CookieValue(value = "uuid")
            final String uuid
    ) throws PersonRegisterException {

        return new WebAsyncTask<>(new Callable<String>() {

            @Override
            public String call() throws Exception {
                if (errors.hasErrors()) {
                    return "/registration";
                }
                if (personForMvc.getBirthDate().after(new Date())) {
                    throw new PersonRegisterException(uuid);
                }
                personForMvc.setUuid(UUID.fromString(uuid));

                System.out.println("User registered " + personForMvc);
                return "redirect:/helloUser/" + personForMvc.getName();
            }
        });

    }

    @RequestMapping(value = "/registration/registerPersonDAsync", method = RequestMethod.POST)
    public DeferredResult<String> registerPersonDAsync(
            @ModelAttribute("personForm")
            @Valid
            final PersonForMvc personForMvc,
            final Errors errors,
            @CookieValue(value = "uuid")
            final String uuid
    ) throws PersonRegisterException {
        final DeferredResult<String> task = new DeferredResult<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new Runnable() {

            @Override
            public void run() {
                if (errors.hasErrors()) {
                    task.setResult("/registration");
                }
                if (personForMvc.getBirthDate().after(new Date())) {
                    task.setErrorResult(new PersonRegisterException(uuid));
                }
                personForMvc.setUuid(UUID.fromString(uuid));

                task.setResult("redirect:/helloUser/" + personForMvc.getName());
                System.out.println("User registered " + personForMvc);
            }
        });

        return task;
    }

    //    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Date is wrong")
    @ExceptionHandler(PersonRegisterException.class)
    public ModelAndView handlePersonRegisterException(PersonRegisterException ex) {

        ModelAndView modelMap = new ModelAndView();
        System.out.println(ex.getUuid());
        modelMap.addObject("uuid", ex.getUuid());
        modelMap.setViewName("registration_error");
        return modelMap;
    }
}
