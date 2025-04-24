package com.mailSeduler.ms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class ServiceFill {
@Autowired
    private RepoSed repoSed;


    public Form addDetails(Form form){
        form.setSubmittedAt(LocalDateTime.now());
       return repoSed.save(form);
    }

    public void delsev(String id) {
        repoSed.deleteById(id);
    }

    public Form update(String id, Form upform) {
        return repoSed.findById(id).map(form -> {
            form.setName(upform.getName());
            form.setEmail(upform.getEmail());
            form.setGender(upform.getGender());
            form.setOccupation(upform.getOccupation());
            form.setCountry(upform.getCountry());
            return repoSed.save(form); // Save and return the updated form
        }).orElseGet(() -> {
            System.out.println("No ID present");
            return null; // Return null if ID is not found
        });
    }









}
