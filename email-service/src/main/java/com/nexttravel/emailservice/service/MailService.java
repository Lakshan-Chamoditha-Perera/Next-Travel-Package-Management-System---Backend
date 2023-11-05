package com.nexttravel.emailservice.service;

import com.nexttravel.emailservice.model.MailStructure;

public interface MailService {
    public void welcome(String mail, MailStructure mailStructure);
}
