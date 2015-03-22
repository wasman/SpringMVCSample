package com.springapp.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Date is wrong")
public class PersonRegisterException extends Exception {

    private final String uuid;

    public PersonRegisterException(String uuid) {

        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
