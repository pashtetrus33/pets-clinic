package ru.geekbrains.clinicservice.services.impl;

import org.springframework.stereotype.Repository;
import ru.geekbrains.clinicservice.models.Client;
import ru.geekbrains.clinicservice.services.ClientRepository;

import java.util.List;

@Repository
public class H2ClientRepository implements ClientRepository {
    @Override
    public int create(Client item) {
        return 0;
    }

    @Override
    public int update(Client item) {
        return 0;
    }

    @Override
    public int delete(Integer integer) {
        return 0;
    }

    @Override
    public Client getById(Integer integer) {
        return null;
    }

    @Override
    public List<Client> getAll() {
        return null;
    }
}
