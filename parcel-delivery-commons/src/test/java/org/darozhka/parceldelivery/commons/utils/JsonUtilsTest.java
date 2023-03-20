package org.darozhka.parceldelivery.commons.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


/**
 * @author S.Darozhka
 */
class JsonUtilsTest {


    private static final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void getStringValueTest() {
        final String existingFieldName = "stringField";
        final String existingFieldValue = "Some value";
        final String nonExistingFieldName = "not_exist";

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put(existingFieldName, existingFieldValue);

        String actual = JsonUtils.getStringValue(objectNode, existingFieldName);
        Assertions.assertEquals(existingFieldValue, actual);

        actual = JsonUtils.getStringValue(objectNode, nonExistingFieldName);
        Assertions.assertNull(actual);
    }

    @Test
    void getIntegerValueTest() {
        final String existingFieldName = "integerField";
        final Integer existingFieldValue = 5;
        final String nonExistingFieldName = "not_exist";

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put(existingFieldName, existingFieldValue);

        Integer actual = JsonUtils.getIntegerValue(objectNode, existingFieldName);
        Assertions.assertEquals(existingFieldValue, actual);

        actual = JsonUtils.getIntegerValue(objectNode, nonExistingFieldName);
        Assertions.assertNull(actual);
    }

    @Test
    void getBooleanValueTest() {
        final String existingFieldName = "booleanField";
        final Boolean existingFieldValue = Boolean.TRUE;
        final String nonExistingFieldName = "not_exist";

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put(existingFieldName, existingFieldValue);

        Boolean actual = JsonUtils.getBooleanValue(objectNode, existingFieldName);
        Assertions.assertEquals(existingFieldValue, actual);

        actual = JsonUtils.getBooleanValue(objectNode, nonExistingFieldName);
        Assertions.assertNull(actual);
    }

}