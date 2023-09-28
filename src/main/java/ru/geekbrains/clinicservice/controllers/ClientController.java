package ru.geekbrains.clinicservice.controllers;

import org.springframework.web.bind.annotation.*;
import ru.geekbrains.clinicservice.models.Client;
import ru.geekbrains.clinicservice.models.requests.ClientRequest;
import ru.geekbrains.clinicservice.services.ClientRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clinic/clients")
public class ClientController {
    ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping(value = "/getall")
    public List<Client> showAll() {
        return clientRepository.getAll();
    }

    @GetMapping(value = "/getbyid/{clientId}")
    public Client getById(@PathVariable(name = "clientId") int clientId) {
        return clientRepository.getById(clientId);
    }

    @DeleteMapping(value = "/delete/{clientId}")
    public int delete(@PathVariable(name = "clientId") int clientId) {
        return clientRepository.delete(clientId);
    }

    @PostMapping(value = "/create")
    public int create(@RequestBody ClientRequest createRequest) {

        return clientRepository.create(new Client(createRequest.getDocument(), createRequest.getFirstname(), createRequest.getSurname(), createRequest.getPatronymic(), createRequest.birthday));
    }

    @PutMapping(value = "/update/{clientId}")
    public int create(@RequestBody ClientRequest updateRequest, @PathVariable(name = "clientId") int clientId) {

        return clientRepository.update(new Client(clientId, updateRequest.getDocument(), updateRequest.getFirstname(), updateRequest.getSurname(), updateRequest.getPatronymic(), updateRequest.birthday));
    }
}
