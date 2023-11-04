package com.nexttravel.emailservice.controller;

import com.nexttravel.emailservice.model.MailStructure;
import com.nexttravel.emailservice.payload.StandardMessageResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class MailController {
    @PostMapping("/send/{mail}")
    public StandardMessageResponse send(@RequestParam String mail, @RequestParam MailStructure mailStructure) {
        return new StandardMessageResponse("Email sent successfully", mailStructure);
    }
}
