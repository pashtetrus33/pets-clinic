package ru.geekbrains.clinicservice.models;

import java.time.LocalDate;

public class Pet {

    //region private fields
    private int petId;
    private int clientId;
    private String name;
    private LocalDate birthday;
    //endregion

    //region constructors
    public Pet() {
    }

    public Pet(int clientId, String name, LocalDate birthday) {
        this.clientId = clientId;
        this.name = name;
        this.birthday = birthday;
    }

    public Pet(int petId, int clientId, String name, LocalDate birthday) {
        this(clientId, name, birthday);
        this.petId = petId;
    }
    //endregion

    //region getters and setters

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }


    //endregion
}
