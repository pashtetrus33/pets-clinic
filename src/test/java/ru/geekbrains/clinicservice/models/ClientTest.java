package ru.geekbrains.clinicservice.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class ClientTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Client#Client()}
     *   <li>{@link Client#setBirthday(LocalDate)}
     *   <li>{@link Client#setClientId(int)}
     *   <li>{@link Client#setDocument(String)}
     *   <li>{@link Client#setFirstname(String)}
     *   <li>{@link Client#setPatronymic(String)}
     *   <li>{@link Client#setSurname(String)}
     *   <li>{@link Client#getBirthday()}
     *   <li>{@link Client#getClientId()}
     *   <li>{@link Client#getDocument()}
     *   <li>{@link Client#getFirstname()}
     *   <li>{@link Client#getPatronymic()}
     *   <li>{@link Client#getSurname()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Client actualClient = new Client();
        LocalDate birthday = LocalDate.of(1970, 1, 1);
        actualClient.setBirthday(birthday);
        actualClient.setClientId(1);
        actualClient.setDocument("Document");
        actualClient.setFirstname("Jane");
        actualClient.setPatronymic("Patronymic");
        actualClient.setSurname("Doe");
        LocalDate actualBirthday = actualClient.getBirthday();
        int actualClientId = actualClient.getClientId();
        String actualDocument = actualClient.getDocument();
        String actualFirstname = actualClient.getFirstname();
        String actualPatronymic = actualClient.getPatronymic();
        assertSame(birthday, actualBirthday);
        assertEquals(1, actualClientId);
        assertEquals("Document", actualDocument);
        assertEquals("Jane", actualFirstname);
        assertEquals("Patronymic", actualPatronymic);
        assertEquals("Doe", actualClient.getSurname());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Client#Client(int, String, String, String, String, LocalDate)}
     *   <li>{@link Client#setBirthday(LocalDate)}
     *   <li>{@link Client#setClientId(int)}
     *   <li>{@link Client#setDocument(String)}
     *   <li>{@link Client#setFirstname(String)}
     *   <li>{@link Client#setPatronymic(String)}
     *   <li>{@link Client#setSurname(String)}
     *   <li>{@link Client#getBirthday()}
     *   <li>{@link Client#getClientId()}
     *   <li>{@link Client#getDocument()}
     *   <li>{@link Client#getFirstname()}
     *   <li>{@link Client#getPatronymic()}
     *   <li>{@link Client#getSurname()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        Client actualClient = new Client(1, "Doe", "Jane", "Patronymic", "Document", LocalDate.of(1970, 1, 1));
        LocalDate birthday = LocalDate.of(1970, 1, 1);
        actualClient.setBirthday(birthday);
        actualClient.setClientId(1);
        actualClient.setDocument("Document");
        actualClient.setFirstname("Jane");
        actualClient.setPatronymic("Patronymic");
        actualClient.setSurname("Doe");
        LocalDate actualBirthday = actualClient.getBirthday();
        int actualClientId = actualClient.getClientId();
        String actualDocument = actualClient.getDocument();
        String actualFirstname = actualClient.getFirstname();
        String actualPatronymic = actualClient.getPatronymic();
        assertSame(birthday, actualBirthday);
        assertEquals(1, actualClientId);
        assertEquals("Document", actualDocument);
        assertEquals("Jane", actualFirstname);
        assertEquals("Patronymic", actualPatronymic);
        assertEquals("Doe", actualClient.getSurname());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Client#Client(String, String, String, String, LocalDate)}
     *   <li>{@link Client#setBirthday(LocalDate)}
     *   <li>{@link Client#setClientId(int)}
     *   <li>{@link Client#setDocument(String)}
     *   <li>{@link Client#setFirstname(String)}
     *   <li>{@link Client#setPatronymic(String)}
     *   <li>{@link Client#setSurname(String)}
     *   <li>{@link Client#getBirthday()}
     *   <li>{@link Client#getClientId()}
     *   <li>{@link Client#getDocument()}
     *   <li>{@link Client#getFirstname()}
     *   <li>{@link Client#getPatronymic()}
     *   <li>{@link Client#getSurname()}
     * </ul>
     */
    @Test
    void testConstructor3() {
        Client actualClient = new Client("Doe", "Jane", "Patronymic", "Document", LocalDate.of(1970, 1, 1));
        LocalDate birthday = LocalDate.of(1970, 1, 1);
        actualClient.setBirthday(birthday);
        actualClient.setClientId(1);
        actualClient.setDocument("Document");
        actualClient.setFirstname("Jane");
        actualClient.setPatronymic("Patronymic");
        actualClient.setSurname("Doe");
        LocalDate actualBirthday = actualClient.getBirthday();
        int actualClientId = actualClient.getClientId();
        String actualDocument = actualClient.getDocument();
        String actualFirstname = actualClient.getFirstname();
        String actualPatronymic = actualClient.getPatronymic();
        assertSame(birthday, actualBirthday);
        assertEquals(1, actualClientId);
        assertEquals("Document", actualDocument);
        assertEquals("Jane", actualFirstname);
        assertEquals("Patronymic", actualPatronymic);
        assertEquals("Doe", actualClient.getSurname());
    }
}

