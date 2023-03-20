package org.darozhka.parceldelivery.iam.domain;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.lang3.Validate;
import org.darozhka.parceldelivery.iam.jackson.ParcelDeliveryUserDetailsDeserializer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author S.Darozhka
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonDeserialize(using = ParcelDeliveryUserDetailsDeserializer.class)
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParcelDeliveryUserDetails implements UserDetails {

    private Integer id;
    private String username;
    private String password;
    private Boolean isActive;
    private Collection<? extends GrantedAuthority> authorities;

    public ParcelDeliveryUserDetails(Integer id,
                                     String username,
                                     String password,
                                     Boolean isActive,
                                     Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.authorities = authorities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static ParcelDeliveryUserDetails getInstance(User user) {
        Validate.notNull(user, "User is null");

        return new ParcelDeliveryUserDetails(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getActive(),
                new HashSet<>(Arrays.asList(user.getRole())));
    }

    public static ParcelDeliveryUserDetails getInstance(Integer id,
                                                        String username,
                                                        String password,
                                                        Boolean isActive,
                                                        Collection<? extends GrantedAuthority> authorities) {
        return new ParcelDeliveryUserDetails(
                id,
                username,
                password,
                isActive,
                authorities);
    }
}
