package ru.geekbrains.clinicservice.models;

import java.time.LocalDate;

public class Client {

    //region private fields
    private int clientId;
    private String document;
    private String surname;
    private String firstname;
    private String patronymic;
    private LocalDate birthday;


    //endregion\

    //region constructors

    public Client() {
    }

    public Client(String document, String surname, String firstname, String patronymic, LocalDate birthday) {
        this.document = document;
        this.surname = surname;
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.birthday = birthday;
    }

    public Client(int clientId, String document, String surname, String firstname, String patronymic, LocalDate birthday) {
        this(document, surname, firstname, patronymic, birthday);
        this.clientId = clientId;
    }

    //endregion

    //region getters and setters
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
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

    //endregion
}
