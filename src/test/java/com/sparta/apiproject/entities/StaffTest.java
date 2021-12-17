package com.sparta.apiproject.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.net.URL;

public class StaffTest {

    @Test
    public void staffTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Staff staff = mapper.readValue(new URL("http://localhost:8080/sakila/staff?id=2"), Staff.class);
        assertEquals("Jon", staff.getFirstName());
    }


}
