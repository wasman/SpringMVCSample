package com.springapp.mvc.multipart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class FileUploadController {

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String register() {

        return "/upload";
    }

    @RequestMapping(value = "/uploadAsync", method = RequestMethod.GET)
    public String registerAsync() {

        return "/uploadAsync";
    }

    @RequestMapping(value = "/uploadMultipleAsync", method = RequestMethod.GET)
    public String uploadMultipleAsync() {

        return "/uploadMultipleAsync";
    }

    @RequestMapping(value = "/uploadSuccess", method = RequestMethod.GET)
    public String uploadSuccess() {

        return "/uploadSuccess";
    }

    @RequestMapping(value = "/uploadFailure", method = RequestMethod.GET)
    public String uploadFailure() {

        return "/uploadFailure";
    }

    @RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
    public String handleFormUpload(
            @RequestParam("name") String name,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            // saveData
            System.out.println(file.getOriginalFilename());
            return "redirect:uploadSuccess";
        }

        return "redirect:uploadFailure";
    }

    @RequestMapping(value = "/uploadFormAsync", method = RequestMethod.POST)
    public String handleFormUploadAsync(
            @RequestParam("name") String name,
            @RequestParam("file") Part file
    ) throws IOException {

        InputStream inputStream = file.getInputStream();
        System.out.println(file.getName());
        // store bytes from uploaded file somewhere

        return "redirect:uploadSuccess";
    }

    @RequestMapping(value = "/uploadFormMultipleAsync", method = RequestMethod.POST)
    public String handleFormUploadAsync(
            @RequestParam("name") String[] names,
            @RequestParam("file") Part[] files
    ) throws IOException {
        System.out.println(names);
        for (Part file : files) {
            InputStream inputStream = file.getInputStream();

            // save data
        }
        return "redirect:uploadSuccess";
    }

}