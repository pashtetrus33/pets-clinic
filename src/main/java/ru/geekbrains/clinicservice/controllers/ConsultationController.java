package ru.geekbrains.clinicservice.controllers;

import org.springframework.web.bind.annotation.*;
import ru.geekbrains.clinicservice.models.Consultation;
import ru.geekbrains.clinicservice.models.requests.ConsultationRequest;
import ru.geekbrains.clinicservice.services.ConsultationRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clinic/consultations")
public class ConsultationController {
    ConsultationRepository consultationRepository;

    public ConsultationController(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    @GetMapping(value = "/getall")
    public List<Consultation> showAll() {
        return consultationRepository.getAll();
    }

    @GetMapping(value = "/getbyid/{consultationId}")
    public Consultation getById(@PathVariable(name = "consultationId") int consultationId) {
        return consultationRepository.getById(consultationId);
    }

    @DeleteMapping(value = "/delete/{consultationId}")
    public int delete(@PathVariable(name = "consultationId") int consultationId) {
        return consultationRepository.delete(consultationId);
    }

    @PostMapping(value = "/create")
    public int create(@RequestBody ConsultationRequest consultationRequest) {

        return consultationRepository.create(new Consultation(consultationRequest.getClientId(), consultationRequest.getPetId(), consultationRequest.getDescription(),
                consultationRequest.getConsultationDate()));
    }

    @PutMapping(value = "/update/{petId}")
    public int create(@RequestBody ConsultationRequest updateRequest, @PathVariable(name = "consultationId") int consultationId) {

        return consultationRepository.update(new Consultation(consultationId, updateRequest.getClientId(), updateRequest.getPetId(), updateRequest.getDescription(),
                updateRequest.getConsultationDate()));
    }
}
