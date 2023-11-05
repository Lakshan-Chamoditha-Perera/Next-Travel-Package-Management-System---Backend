package com.nexttravel.emailservice.controller;

import com.nexttravel.emailservice.payload.StandardMessageResponse;
import com.nexttravel.emailservice.service.MailService;
import com.nexttravel.emailservice.service.custom.MailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MailController {
    private final MailService mailService;
    @PostMapping("/send/welcome")
    public StandardMessageResponse send(@RequestHeader String mail) {
        mailService.welcome(mail, null);
        return new StandardMessageResponse("Email sent successfully", true);
    }
}
