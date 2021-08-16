package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;

public class TestDataBuild {
    ObjectMapper objectMapper = new ObjectMapper();

    public  User createUserPayload(String payloadAsString) throws JsonProcessingException {
        User user = objectMapper.readValue(payloadAsString, User.class);
        return user;
    }
}
