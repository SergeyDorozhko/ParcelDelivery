package org.darozhka.parceldelivery.commons.utils;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author S.Darozhka
 */
public class JsonUtils {

    public static String getStringValue(JsonNode jsonNode) {
        return (jsonNode != null && jsonNode.isTextual()) ? jsonNode.asText() : null;
    }

    public static String getStringValue(JsonNode jsonNode, String fieldName) {
        if (jsonNode == null) {
            return null;
        }
        JsonNode value = jsonNode.findValue(fieldName);
        return getStringValue(value);
    }

    public static Boolean getBooleanValue(JsonNode jsonNode, String fieldName) {
        if (jsonNode == null) {
            return null;
        }
        JsonNode value = jsonNode.findValue(fieldName);
        return (value != null && value.isBoolean()) ?
                value.asBoolean() :
                null;
    }

    public static Integer getIntegerValue(JsonNode jsonNode, String fieldName) {
        if (jsonNode == null) {
            return null;
        }
        JsonNode value = jsonNode.findValue(fieldName);
        return (value != null && value.isInt()) ?
                value.asInt() :
                null;
    }

    private static <T> T getValue(JsonNode jsonNode,
                                 String fieldName,
                                 TypeReference<T> valueTypeReference,
                                 ObjectMapper mapper) {
        if (jsonNode == null) {
            return null;
        }
        JsonNode value = jsonNode.findValue(fieldName);
        return (value != null && value.isContainerNode()) ?
                mapper.convertValue(value, valueTypeReference) :
                null;
    }

    public static Set<? extends GrantedAuthority> getAuthoritiesSet(JsonNode jsonNode,
                                                                    String fieldName,
                                                                    ObjectMapper mapper) {
        return getValue(
                jsonNode,
                fieldName,
                new TypeReference<Set<? extends GrantedAuthority>>() {},
                mapper);
    }


    private JsonUtils() {
    }
}
