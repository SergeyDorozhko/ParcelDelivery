package org.darozhka.parceldelivery.iam.jackson;

import java.io.IOException;
import java.util.Objects;

import org.darozhka.parceldelivery.commons.utils.JsonUtils;
import org.darozhka.parceldelivery.iam.domain.ParcelDeliveryUserDetails;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author S.Darozhka
 */
public class ParcelDeliveryUserDetailsDeserializer extends JsonDeserializer<ParcelDeliveryUserDetails> {

    @Override
    public ParcelDeliveryUserDetails deserialize(
            JsonParser jsonParser,
            DeserializationContext deserializationContext) throws IOException, JacksonException {
        if (Objects.isNull(jsonParser)) {
            return null;
        }

        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode root = mapper.readTree(jsonParser);

        return ParcelDeliveryUserDetails.getInstance(
                JsonUtils.getIntegerValue(root, "id"),
                JsonUtils.getStringValue(root, "username"),
                JsonUtils.getStringValue(root, "password"),
                JsonUtils.getBooleanValue(root, "isActive"),
                JsonUtils.getAuthoritiesSet(root, "authorities", mapper));
    }
}
