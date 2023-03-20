package org.darozhka.parceldelivery.iam.domain;

import org.darozhka.parceldelivery.iam.jackson.SecurityRoleDeserializer;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author S.Darozhka
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonDeserialize(using = SecurityRoleDeserializer.class)
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public enum SecurityRole implements GrantedAuthority {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER"),
    COURIER("ROLE_COURIER");

    private String name;

    SecurityRole(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }

}