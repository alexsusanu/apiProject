package com.sparta.apiproject.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.net.URL;

public class StaffTest {
    private static ObjectMapper mapper;
    @BeforeAll
    public static void setup(){
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
    }

    @Test
    public void staffFirstNameTest() throws IOException {
        Staff staff = mapper.readValue(new URL("http://localhost:8080/sakila/staff?id=2"), Staff.class);
        assertEquals("Jon", staff.getFirstName());
    }

    @Test
    public void staffLastNameTest() throws IOException {
        Staff staff = mapper.readValue(new URL("http://localhost:8080/sakila/staff?id=2"), Staff.class);
        assertEquals("Stephens", staff.getLastName());
    }

    @Test
    public void staffEmailTest() throws IOException {
        Staff staff = mapper.readValue(new URL("http://localhost:8080/sakila/staff?id=1"), Staff.class);
        assertEquals("Mike.Hillyer@sakilastaff.com", staff.getEmail());
    }

    @Test
    public void staffStoreIDTest() throws IOException {
        Staff staff = mapper.readValue(new URL("http://localhost:8080/sakila/staff?id=1"), Staff.class);
        assertEquals(1, staff.getStore().getId());
    }

}
