package ru.geekbrains.clinicservice.controllers;

import org.springframework.web.bind.annotation.*;
import ru.geekbrains.clinicservice.models.Client;
import ru.geekbrains.clinicservice.models.Pet;
import ru.geekbrains.clinicservice.models.requests.ClientRequest;
import ru.geekbrains.clinicservice.models.requests.PetRequest;
import ru.geekbrains.clinicservice.services.ClientRepository;
import ru.geekbrains.clinicservice.services.PetRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clinic/pets")
public class PetController {
    PetRepository petRepository;

    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @GetMapping(value = "/getall")
    public List<Pet> showAll() {
        return petRepository.getAll();
    }

    @GetMapping(value = "/getbyid/{petId}")
    public Pet getById(@PathVariable(name = "petId") int petId) {
        return petRepository.getById(petId);
    }

    @DeleteMapping(value = "/delete/{petId}")
    public int delete(@PathVariable(name = "petId") int petId) {
        return petRepository.delete(petId);
    }

    @PostMapping(value = "/create")
    public int create(@RequestBody PetRequest petRequest) {

        return petRepository.create(new Pet(petRequest.getClientId(), petRequest.getName(), petRequest.getBirthday()));
    }

    @PutMapping(value = "/update/{petId}")
    public int update(@RequestBody PetRequest updateRequest, @PathVariable(name = "petId") int petId) {

        return petRepository.update(new Pet(petId, updateRequest.getClientId(), updateRequest.getName(), updateRequest.getBirthday()));
    }
}
