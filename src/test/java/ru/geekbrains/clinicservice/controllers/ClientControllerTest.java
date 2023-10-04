package ru.geekbrains.clinicservice.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.geekbrains.clinicservice.models.Client;
import ru.geekbrains.clinicservice.models.requests.ClientRequest;
import ru.geekbrains.clinicservice.services.ClientRepository;
import ru.geekbrains.clinicservice.services.impl.H2ClientRepository;

@ContextConfiguration(classes = {ClientController.class})
@ExtendWith(SpringExtension.class)
class ClientControllerTest {
    @Autowired
    private ClientController clientController;

    @MockBean
    private ClientRepository clientRepository;

    /**
     * Method under test: {@link ClientController#getById(int)}
     */
    @Test
    void testGetById() throws Exception {
        when(clientRepository.getById(Mockito.<Integer>any())).thenReturn(new Client());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/clinic/clients/getbyid/{clientId}", 1);
        MockMvcBuilders.standaloneSetup(clientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"clientId\":0,\"document\":null,\"surname\":null,\"firstname\":null,\"patronymic\":null,\"birthday\":null}"));
    }

    /**
     * Method under test: {@link ClientController#delete(int)}
     */
    @Test
    void testDelete() throws Exception {
        when(clientRepository.delete(Mockito.<Integer>any())).thenReturn(1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v1/clinic/clients/delete/{clientId}", 1);
        MockMvcBuilders.standaloneSetup(clientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    /**
     * Method under test: {@link ClientController#create(ClientRequest)}
     */
    @Test
    void testCreate() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDate` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: ru.geekbrains.clinicservice.models.requests.ClientRequest["birthday"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1308)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:479)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:318)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4719)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3964)
        //   See https://diff.blue/R013 to resolve this issue.

        ClientController clientController = new ClientController(new H2ClientRepository());

        ClientRequest createRequest = new ClientRequest();
        createRequest.setBirthday(LocalDate.of(1970, 1, 1));
        createRequest.setDocument("Document");
        createRequest.setFirstname("Jane");
        createRequest.setPatronymic("Patronymic");
        createRequest.setSurname("Doe");
        assertEquals(0, clientController.create(createRequest));
    }

    /**
     * Method under test: {@link ClientController#update(ClientRequest, int)}
     */
    @Test
    void testUpdate() throws Exception {
        when(clientRepository.update(Mockito.<Client>any())).thenReturn(1);

        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setBirthday(null);
        clientRequest.setDocument("Document");
        clientRequest.setFirstname("Jane");
        clientRequest.setPatronymic("Patronymic");
        clientRequest.setSurname("Doe");
        String content = (new ObjectMapper()).writeValueAsString(clientRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/v1/clinic/clients/update/{clientId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(clientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    /**
     * Method under test: {@link ClientController#showAll()}
     */
    @Test
    void testShowAll() throws Exception {
        when(clientRepository.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/clinic/clients/getall");
        MockMvcBuilders.standaloneSetup(clientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

