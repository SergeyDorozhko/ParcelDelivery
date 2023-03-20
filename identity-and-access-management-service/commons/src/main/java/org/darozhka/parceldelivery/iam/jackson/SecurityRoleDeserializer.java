package org.darozhka.parceldelivery.iam.jackson;

import java.io.IOException;
import java.util.Objects;

import org.darozhka.parceldelivery.commons.utils.JsonUtils;
import org.darozhka.parceldelivery.iam.domain.SecurityRole;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author S.Darozhka
 */
public class SecurityRoleDeserializer extends JsonDeserializer<SecurityRole> {

    @Override
    public SecurityRole deserialize(JsonParser jsonParser,
                                    DeserializationContext deserializationContext) throws IOException, JacksonException {
        if (Objects.isNull(jsonParser)) {
            return null;
        }

        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode root = mapper.readTree(jsonParser);

        return SecurityRole.valueOf(JsonUtils.getStringValue(root));
    }
}
