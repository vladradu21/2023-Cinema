package com.projectps.cinema.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailSenderService {

    void sendSimpleEmail(String toEmail, String subject, String body);
}
