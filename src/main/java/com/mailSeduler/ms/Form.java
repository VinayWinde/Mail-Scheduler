package com.mailSeduler.ms;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="Informations")
public class Form {
    @Id
    private String id;
    private String name;
    private String email;
    private String gender;
    private String occupation;
    private String country;
    private LocalDateTime submittedAt;

    public Form(String id, String name, String email, String gender, String occupation, String country, LocalDateTime submittedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.occupation = occupation;
        this.country = country;
        this.submittedAt = submittedAt;
    }
    public Form(){

    }

    @Override
    public String toString() {
        return "Form{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", occupation='" + occupation + '\'' +
                ", country='" + country + '\'' +
                ", submittedAt=" + submittedAt +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
}