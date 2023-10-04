package ru.geekbrains.clinicservice.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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
import ru.geekbrains.clinicservice.models.Consultation;
import ru.geekbrains.clinicservice.models.requests.ConsultationRequest;
import ru.geekbrains.clinicservice.services.ConsultationRepository;
import ru.geekbrains.clinicservice.services.impl.SqlLiteConsultationRepository;

@ContextConfiguration(classes = {ConsultationController.class})
@ExtendWith(SpringExtension.class)
class ConsultationControllerTest {
    @Autowired
    private ConsultationController consultationController;

    @MockBean
    private ConsultationRepository consultationRepository;

    /**
     * Method under test: {@link ConsultationController#getById(int)}
     */
    @Test
    void testGetById() throws Exception {
        when(consultationRepository.getById(Mockito.<Integer>any())).thenReturn(new Consultation());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/clinic/consultations/getbyid/{consultationId}", 1);
        MockMvcBuilders.standaloneSetup(consultationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"consultationId\":0,\"clientId\":0,\"petId\":0,\"description\":null,\"consultationDate\":null}"));
    }

    /**
     * Method under test: {@link ConsultationController#delete(int)}
     */
    @Test
    void testDelete() throws Exception {
        when(consultationRepository.delete(Mockito.<Integer>any())).thenReturn(1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v1/clinic/consultations/delete/{consultationId}", 1);
        MockMvcBuilders.standaloneSetup(consultationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    /**
     * Method under test: {@link ConsultationController#create(ConsultationRequest)}
     */
    @Test
    void testCreate() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDate` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: ru.geekbrains.clinicservice.models.requests.ConsultationRequest["consultationDate"])
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

        SqlLiteConsultationRepository consultationRepository = mock(SqlLiteConsultationRepository.class);
        when(consultationRepository.create(Mockito.<Consultation>any())).thenReturn(1);
        ConsultationController consultationController = new ConsultationController(consultationRepository);

        ConsultationRequest consultationRequest = new ConsultationRequest();
        consultationRequest.setClientId(1);
        consultationRequest.setConsultationDate(LocalDate.of(1970, 1, 1));
        consultationRequest.setDescription("The characteristics of someone or something");
        consultationRequest.setPetId(1);
        assertEquals(1, consultationController.create(consultationRequest));
        verify(consultationRepository).create(Mockito.<Consultation>any());
    }

    /**
     * Method under test: {@link ConsultationController#update(ConsultationRequest, int)}
     */
    @Test
    void testUpdate() throws Exception {
        when(consultationRepository.update(Mockito.<Consultation>any())).thenReturn(1);

        ConsultationRequest consultationRequest = new ConsultationRequest();
        consultationRequest.setClientId(1);
        consultationRequest.setConsultationDate(null);
        consultationRequest.setDescription("The characteristics of someone or something");
        consultationRequest.setPetId(1);
        String content = (new ObjectMapper()).writeValueAsString(consultationRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/v1/clinic/consultations/update/{consultationId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(consultationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    /**
     * Method under test: {@link ConsultationController#showAll()}
     */
    @Test
    void testShowAll() throws Exception {
        when(consultationRepository.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/clinic/consultations/getall");
        MockMvcBuilders.standaloneSetup(consultationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

