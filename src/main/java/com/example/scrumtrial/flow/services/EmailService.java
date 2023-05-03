package com.example.scrumtrial.flow.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.IncomingPhoneNumber;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.scrumtrial.models.entities.ValidationEntity;
import com.example.scrumtrial.models.repositories.ValidationRepository;
import com.sendgrid.*;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Service
@Slf4j
public class EmailService {

    private final String key;
    private String number;
    private final ValidationRepository vRepo;
    private String sid = "${TWILIO_ACCOUNT_SID}";
    private String token = "${TWILIO_AUTH_TOKEN}";

    public EmailService(@Value("${SENDGRID_API_KEY}") String key,
            final ValidationRepository vRepo) {
        this.key = key;
        this.vRepo = vRepo;
    }

    public void sendEmail(final String fromEmail, final String toEmail, String subject, String body) {
        Email from = new Email(fromEmail);
        Email to = new Email(toEmail);
        Content content = new Content("text/plain", body);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(this.key);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            log.info(
                    "Email sent to {} with subject {} and body {}. Response status code {}",
                    toEmail,
                    subject,
                    body,
                    response.getStatusCode());

        } catch (IOException ex) {
            log.error(
                    "Error sending email to {} with subject {} and body {}",
                    toEmail,
                    subject,
                    body,
                    ex);
        }
    }

    public void sendValidationCodeEmail(final String emailToValidate) throws IllegalArgumentException {

        if (emailToValidate == null || emailToValidate.isBlank()) {
            throw new IllegalArgumentException("emailToValidate cannot be null or blank");
        }

        String code = String.valueOf(System.currentTimeMillis()).substring(0, 6);
        Email from = new Email("intec.brussel@mail.be");
        Email to = new Email(emailToValidate);
        // Generate a validation code from timestamp
        String validationMessage = "Validation code: " + code;
        Content content = new Content("text/plain", validationMessage);
        Mail mail = new Mail(from, "Email", to, content);

        SendGrid sg = new SendGrid(this.key);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            log.info(
                    "Email sent to {} with subject {} and body {}. Response status code {}",
                    validationMessage,
                    "Email validation",
                    validationMessage,
                    response.getStatusCode());
            
            if(response.getStatusCode() != 202){
                throw new IOException("Error sending email");
            }

            // save the validation code to the database
            ValidationEntity v = vRepo.save(
                new ValidationEntity(emailToValidate,code)
            );

            log.info(
                    "Validation code {} saved to database with id {}",
                    v.getCode(),
                    v.getId()
            );

        } catch (IOException ex) {
            log.error(
                    "Error sending email to {} with subject {} and body {}",
                    validationMessage,
                    "Email validation",
                    validationMessage,
                    ex);
        }
    }


}
