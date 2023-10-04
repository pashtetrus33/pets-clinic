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
import ru.geekbrains.clinicservice.models.Pet;
import ru.geekbrains.clinicservice.models.requests.PetRequest;
import ru.geekbrains.clinicservice.services.PetRepository;
import ru.geekbrains.clinicservice.services.impl.SqlLitePetRepository;

@ContextConfiguration(classes = {PetController.class})
@ExtendWith(SpringExtension.class)
class PetControllerTest {
    @Autowired
    private PetController petController;

    @MockBean
    private PetRepository petRepository;

    /**
     * Method under test: {@link PetController#getById(int)}
     */
    @Test
    void testGetById() throws Exception {
        when(petRepository.getById(Mockito.<Integer>any())).thenReturn(new Pet());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/clinic/pets/getbyid/{petId}",
                1);
        MockMvcBuilders.standaloneSetup(petController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"petId\":0,\"clientId\":0,\"name\":null,\"birthday\":null}"));
    }

    /**
     * Method under test: {@link PetController#delete(int)}
     */
    @Test
    void testDelete() throws Exception {
        when(petRepository.delete(Mockito.<Integer>any())).thenReturn(1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/clinic/pets/delete/{petId}",
                1);
        MockMvcBuilders.standaloneSetup(petController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    /**
     * Method under test: {@link PetController#create(PetRequest)}
     */
    @Test
    void testCreate() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDate` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: ru.geekbrains.clinicservice.models.requests.PetRequest["birthday"])
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

        SqlLitePetRepository petRepository = mock(SqlLitePetRepository.class);
        when(petRepository.create(Mockito.<Pet>any())).thenReturn(1);
        PetController petController = new PetController(petRepository);

        PetRequest petRequest = new PetRequest();
        petRequest.setBirthday(LocalDate.of(1970, 1, 1));
        petRequest.setClientId(1);
        petRequest.setName("Bella");
        assertEquals(1, petController.create(petRequest));
        verify(petRepository).create(Mockito.<Pet>any());
    }

    /**
     * Method under test: {@link PetController#update(PetRequest, int)}
     */
    @Test
    void testUpdate() throws Exception {
        when(petRepository.update(Mockito.<Pet>any())).thenReturn(1);

        PetRequest petRequest = new PetRequest();
        petRequest.setBirthday(null);
        petRequest.setClientId(1);
        petRequest.setName("Bella");
        String content = (new ObjectMapper()).writeValueAsString(petRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/clinic/pets/update/{petId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(petController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    /**
     * Method under test: {@link PetController#showAll()}
     */
    @Test
    void testShowAll() throws Exception {
        when(petRepository.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/clinic/pets/getall");
        MockMvcBuilders.standaloneSetup(petController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

