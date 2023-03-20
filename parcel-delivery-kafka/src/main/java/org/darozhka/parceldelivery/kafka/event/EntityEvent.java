package org.darozhka.parceldelivery.kafka.event;

import org.darozhka.parceldelivery.commons.domain.Entity;
import org.darozhka.parceldelivery.commons.utils.MapperUtils;

/**
 * @author S.Darozhka
 */
public abstract class EntityEvent implements Event {

    private Integer id;
    private String data;

    public EntityEvent() {
    }

    public EntityEvent(Entity entity) {
        this(entity.getId(), MapperUtils.toJson(entity));
    }

    public EntityEvent(Integer id, String data) {
        this.id = id;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
