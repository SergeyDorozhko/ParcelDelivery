package org.darozhka.parceldelivery.commons.utils;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author S.Darozhka
 */
public class MapperUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    static {
        MAPPER.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    private MapperUtils() {
    }

    public static String toJson(Object value) {
        if (value == null) {
            return null;
        }

        try {
            return MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException jpe) {
            throw new RuntimeException(jpe);
        }
    }

    public static <T> T fromJson(String json, Class<T> type) {
        Validate.notNull(type, "Type is null");

        if (StringUtils.isBlank(json)) {
            return null;
        }

        try {
            return MAPPER.readValue(json, type);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
}
