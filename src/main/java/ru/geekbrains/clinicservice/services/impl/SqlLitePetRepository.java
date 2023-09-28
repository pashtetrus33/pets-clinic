package ru.geekbrains.clinicservice.services.impl;

import org.springframework.stereotype.Repository;
import ru.geekbrains.clinicservice.models.Pet;
import ru.geekbrains.clinicservice.services.PetRepository;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class SqlLitePetRepository implements PetRepository {
    private final String jdbcUrl = "jdbc:sqlite:clinic.db";
    private static final Logger log = Logger.getLogger(SqlLitePetRepository.class.getName());

    @Override
    public int create(Pet item) {
        log.info("create pet request: " + item.getName());
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO pets VALUES(?,?,?,?)");
            preparedStatement.setInt(2, item.getClientId());
            preparedStatement.setString(3, item.getName());
            preparedStatement.setLong(4, item.getBirthday().getLong(ChronoField.EPOCH_DAY));
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Pet item) {
        log.info("update pet request: " + item.getPetId());
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE pets SET clientId = ?," +
                    "name = ?, birthday =? WHERE petId = ?");
            preparedStatement.setInt(1, item.getClientId());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setLong(3, item.getBirthday().getLong(ChronoField.EPOCH_DAY));
            preparedStatement.setLong(4, item.getPetId());
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Integer petId) {
        log.info("delete pet request: " + petId);
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM pets WHERE petId = ?");

            preparedStatement.setLong(1, petId);
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Pet getById(Integer petId) {
        log.info("getById pet request: " + petId);
        String sql = "SELECT * FROM pets WHERE petId = ?";
        Pet pet = null;
        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, petId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    pet = new Pet(resultSet.getInt("petId"),
                            resultSet.getInt("clientId"),
                            resultSet.getString("name"),
                            LocalDate.ofEpochDay(resultSet.getLong("birthday")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pet;
    }

    @Override
    public List<Pet> getAll() {
        log.info("getAll pet request");
        String sql = "SELECT * FROM pets";
        List<Pet> clients = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    clients.add(new Pet(resultSet.getInt("petId"),
                            resultSet.getInt("clientId"),
                            resultSet.getString("name"),
                            LocalDate.ofEpochDay(resultSet.getLong("birthday"))));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}
