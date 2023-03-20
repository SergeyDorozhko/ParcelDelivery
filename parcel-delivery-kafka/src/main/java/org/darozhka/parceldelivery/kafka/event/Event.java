package org.darozhka.parceldelivery.kafka.event;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author S.Darozhka
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        property = "@class"
)
public interface Event extends Serializable {

    Integer getId();
    String getData();
}
