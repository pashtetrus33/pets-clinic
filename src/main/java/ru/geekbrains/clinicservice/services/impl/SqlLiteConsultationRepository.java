package ru.geekbrains.clinicservice.services.impl;

import org.springframework.stereotype.Repository;
import ru.geekbrains.clinicservice.models.Consultation;
import ru.geekbrains.clinicservice.models.Pet;
import ru.geekbrains.clinicservice.services.ConsultationRepository;
import ru.geekbrains.clinicservice.services.PetRepository;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class SqlLiteConsultationRepository implements ConsultationRepository {
    private final String jdbcUrl = "jdbc:sqlite:clinic.db";
    private static final Logger log = Logger.getLogger(SqlLiteConsultationRepository.class.getName());

    @Override
    public int create(Consultation item) {
        log.info("create consultation request: " + item.getDescription());
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO consultations VALUES(?,?,?,?,?)");
            preparedStatement.setInt(2, item.getClientId());
            preparedStatement.setInt(3, item.getPetId());
            preparedStatement.setString(4, item.getDescription());
            preparedStatement.setLong(5, item.getConsultationDate().getLong(ChronoField.EPOCH_DAY));
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Consultation item) {
        log.info("update consultation request: " + item.getConsultationId());
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE consultations SET clientId = ?," +
                    "petId = ?,description = ?, consultationDate =? WHERE consultationId = ?");
            preparedStatement.setInt(1, item.getClientId());
            preparedStatement.setInt(2, item.getPetId());
            preparedStatement.setString(3, item.getDescription());
            preparedStatement.setLong(4, item.getConsultationDate().getLong(ChronoField.EPOCH_DAY));
            preparedStatement.setLong(5, item.getConsultationId());
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Integer consultationId) {
        log.info("delete consultation request: " + consultationId);
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM consultations WHERE consultationId = ?");

            preparedStatement.setLong(1, consultationId);
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Consultation getById(Integer consultationId) {
        log.info("getById consultation request: " + consultationId);
        String sql = "SELECT * FROM consultations WHERE consultationId = ?";
        Consultation consultation = null;
        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, consultationId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    consultation = new Consultation(resultSet.getInt("consultationId"),
                            resultSet.getInt("clientId"),
                            resultSet.getInt("petId"),
                            resultSet.getString("description"),
                            LocalDate.ofEpochDay(resultSet.getLong("consultationDate")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultation;
    }

    @Override
    public List<Consultation> getAll() {
        log.info("getAll consultation request");
        String sql = "SELECT * FROM consultations";
        List<Consultation> consultations = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    consultations.add(new Consultation(resultSet.getInt("consultationId"),
                            resultSet.getInt("clientId"),
                            resultSet.getInt("petId"),
                            resultSet.getString("description"),
                            LocalDate.ofEpochDay(resultSet.getLong("consultationDate"))));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }
}
