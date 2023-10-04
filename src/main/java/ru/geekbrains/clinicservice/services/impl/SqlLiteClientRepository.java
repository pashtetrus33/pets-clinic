package ru.geekbrains.clinicservice.services.impl;

import org.springframework.stereotype.Repository;
import ru.geekbrains.clinicservice.models.Client;
import ru.geekbrains.clinicservice.services.ClientRepository;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class SqlLiteClientRepository implements ClientRepository {
    private final String jdbcUrl = "jdbc:sqlite:clinic.db";
    private static final Logger log = Logger.getLogger(SqlLiteClientRepository.class.getName());

    @Override
    public int create(Client item) {
        log.info("create client request: " + item.getSurname());
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO clients VALUES(?,?,?,?,?,?)");
            preparedStatement.setString(2, item.getSurname());
            preparedStatement.setString(3, item.getFirstname());
            preparedStatement.setString(4, item.getPatronymic());
            preparedStatement.setString(5, item.getDocument());
            preparedStatement.setLong(6, item.getBirthday().getLong(ChronoField.EPOCH_DAY));
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Client item) {
        log.info("update client request: " + item.getClientId());
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE clients SET surname = ?," +
                    "firstname = ?, patronymic = ?, document = ?, birthday =? WHERE clientId = ?");
            preparedStatement.setString(1, item.getSurname());
            preparedStatement.setString(2, item.getFirstname());
            preparedStatement.setString(3, item.getPatronymic());
            preparedStatement.setString(4, item.getDocument());
            preparedStatement.setLong(5, item.getBirthday().getLong(ChronoField.EPOCH_DAY));
            preparedStatement.setLong(6, item.getClientId());
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Integer clientId) {
        log.info("delete client request: " + clientId);
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM clients WHERE clientId = ?");

            preparedStatement.setLong(1, clientId);
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Client getById(Integer clientId) {
        log.info("getById client request: " + clientId);
        String sql = "SELECT * FROM clients WHERE clientId = ?";
        Client client = null;
        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, clientId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    client = new Client(resultSet.getInt("clientId"),
                            resultSet.getString("surname"),
                            resultSet.getString("firstname"),
                            resultSet.getString("patronymic"),
                            resultSet.getString("document"),
                            LocalDate.ofEpochDay(resultSet.getLong("birthday")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public List<Client> getAll() {
        log.info("getAll client request");
        String sql = "SELECT * FROM clients";
        List<Client> clients = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    clients.add(new Client(resultSet.getInt("clientId"),
                            resultSet.getString("surname"),
                            resultSet.getString("firstname"),
                            resultSet.getString("patronymic"),
                            resultSet.getString("document"),
                            LocalDate.ofEpochDay(resultSet.getLong("birthday"))));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}
