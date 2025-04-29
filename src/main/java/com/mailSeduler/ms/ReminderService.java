package com.mailSeduler.ms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

@Service
public class ReminderService implements SchedulingConfigurer {

    @Autowired
    private RepoSed repoSed;

    @Autowired
    private EmailService emailService;

    private ScheduledFuture<?> scheduledTask;
    private volatile boolean schedulerEnabled = true;
    private final LocalDateTime oneDayAgo = LocalDateTime.now().minusHours(24);

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        scheduledTask = taskRegistrar.getScheduler().scheduleAtFixedRate(
                this::checkReply,
                60000 // Runs every 60 seconds
        );
    }

    @Transactional
    public List<String> checkReply() {
        if (!schedulerEnabled) {
            cancelScheduler();
            return List.of();
        }

        System.out.println("--->>>SCHEDULER triggered@@@@@@@");
        List<Form> forms = repoSed.findAll();
        List<String> emails = new ArrayList<>();

        for (Form form : forms) {
            if (shouldSendReminder(form)) {
                emails.add(form.getEmail());
                sendReminderEmail(form);
            }
        }

        return emails;
    }

    private boolean shouldSendReminder(Form form) {
        return form.getSubmittedAt() != null &&
                form.getSubmittedAt().isBefore(oneDayAgo) &&
                isAnyFieldNull(form);
    }

    private void sendReminderEmail(Form form) {
        emailService.sendEmail(
                form.getEmail(),
                "Reminder to Complete Your Form",
                "Dear user, \n\nWe noticed you haven't completed your form. " +
                        "Please fill all required fields."
        );
    }

    private boolean isAnyFieldNull(Form form) {
        return form.getName() == null ||
                form.getEmail() == null ||
                form.getGender() == null ||
                form.getOccupation() == null ||
                form.getCountry() == null;
    }

    // Call this to stop the scheduler permanently
    public void stopScheduler() {
        schedulerEnabled = false;
    }

    private void cancelScheduler() {
        if (scheduledTask != null && !scheduledTask.isCancelled()) {
            scheduledTask.cancel(false);
            System.out.println("SCHEDULER STOPPED PERMANENTLY");
        }
    }
}