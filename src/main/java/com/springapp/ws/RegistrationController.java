package com.springapp.ws;

import com.springapp.mvc.orm.Person;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistrationController {

    @MessageMapping("/registerSPerson")
    @SendTo("/topic/showResult")
    public Result registerSPerson(Person person) throws Exception {
        Thread.sleep(2000);
        System.out.println("#$%% controller called");
        Result result = new Result(person.getName() + " " + person.getEmail());
        return result;
    }

    @RequestMapping("/registerWithSocket")
    public String start() {
        return "registerWithSocket";
    }
}
