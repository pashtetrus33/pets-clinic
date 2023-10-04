package ru.geekbrains.clinicservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class ClinicServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicServiceApplication.class, args);
        try (Connection connection = configureSqlLiteConnection()) {
            prepareSchema(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection configureSqlLiteConnection() throws SQLException {
        String jdbcUrl = "jdbc:sqlite:clinic.db";
        return DriverManager.getConnection(jdbcUrl);
    }

    public static void prepareSchema(Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE IF EXISTS clients");
        statement.execute("DROP TABLE IF EXISTS pets");
        statement.execute("DROP TABLE IF EXISTS consultations");

        statement.execute("create table clients (" +
                "clientId INTEGER primary key AUTOINCREMENT," +
                "surname VARCHAR(25)," +
                "firstname VARCHAR(25)," +
                "patronymic VARCHAR(25)," +
                "document VARCHAR(50)," +
                "birthday INTEGER)");
        statement.execute("create table pets (petId INTEGER primary key AUTOINCREMENT," +
                "clientId INTEGER," +
                "name VARCHAR(25)," +
                "birthday INTEGER)");
        statement.execute("create table consultations (consultationId INTEGER primary key AUTOINCREMENT," +
                "clientId INTEGER," +
                "petId INTEGER," +
                "consultationDate INTEGER," +
                "description VARCHAR(40))");

        System.out.println("Tables are created");
    }
}
