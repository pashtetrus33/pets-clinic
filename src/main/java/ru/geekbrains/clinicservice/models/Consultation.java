package ru.geekbrains.clinicservice.models;

import java.time.LocalDate;

public class Consultation {

    //region private fields
    private int consultationId;
    private int clientId;
    private int petId;
    private String description;
    private LocalDate consultationDate;
    //endregion

    //region constructors
    public Consultation() {
    }

    public Consultation(int clientId, int petId, String description, LocalDate consultationDate) {
        this.clientId = clientId;
        this.petId = petId;
        this.description = description;
        this.consultationDate = consultationDate;
    }

    public Consultation(int consultationId, int clientId, int petId, String description, LocalDate consultationDate) {
        this(petId, clientId, description, consultationDate);
        this.consultationId = consultationId;
    }
    //endregion


    //region getters and setters

    public int getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(int consultationId) {
        this.consultationId = consultationId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(LocalDate consultationDate) {
        this.consultationDate = consultationDate;
    }


    //endregion
}