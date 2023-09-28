package ru.geekbrains.clinicservice.models.requests;

import java.time.LocalDate;

public class ClientRequest {
    private String document;
    private String surname;
    private String firstname;
    private String patronymic;
    public LocalDate birthday;

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }


    public String getSurname() {
        return surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
