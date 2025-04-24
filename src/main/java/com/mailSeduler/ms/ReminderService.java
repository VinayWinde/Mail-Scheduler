package com.mailSeduler.ms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class    ReminderService {

    @Autowired
    private RepoSed repoSed;

    @Autowired
    private EmailService emailService;

    // Assuming twoMinutesAgo is initialized somewhere, e.g., in the constructor
    private LocalDateTime oneDayAgo = LocalDateTime.now().minusHours(24);

    // Scheduled task that runs every minute to check for incomplete forms


    // Method that checks all forms and returns a list of emails where fields are missing and submittedAt > 2 mins
    @Scheduled(fixedRate = 60000) // every minute
    public List<String> checkReply() {
        System.out.println("--->>>SEDULER triggered@@@@@@@");
        List<Form> forms = repoSed.findAll();
        List<String> emails = new ArrayList<>();

        for (Form form : forms) {
            LocalDateTime submittedAt = form.getSubmittedAt();

            //&& form.getSubmittedAt() != null && form.getSubmittedAt().isBefore(twoMinutesAgo)
            if (isAnyFieldNull(form)&& form.getSubmittedAt().isBefore(oneDayAgo)&& form.getSubmittedAt() != null) {
                emails.add(form.getEmail());
                System.out.println(form.getEmail());
                emailService.sendEmail(form.getEmail(),"Reminder to Complete Your Form","Dear user, \n\n" +
                        "We noticed that you haven't completed your form submission. Please fill in all the required fields and submit it as soon as possible.");
            }
        }
        return emails;
    }

    // Helper method to check if any required field is null
    private boolean isAnyFieldNull(Form form) {
        return form.getName() == null ||
                form.getEmail() == null ||
                form.getGender() == null ||
                form.getOccupation() == null ||
                form.getCountry() == null;
    }

    // Sends a reminder email to the user

}
