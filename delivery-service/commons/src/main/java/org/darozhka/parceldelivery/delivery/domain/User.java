package org.darozhka.parceldelivery.delivery.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.darozhka.parceldelivery.iam.domain.SecurityRole;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author S.Darozhka
 */
@Entity
@Table(name = "app_user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @Id
    private Integer id;

    private String name;

    private String surname;

    private String username;

    private Boolean isActive;

    @Enumerated(EnumType.STRING)
    private SecurityRole role;

    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public SecurityRole getRole() {
        return role;
    }

    public void setRole(SecurityRole role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User that = (User) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(username, that.username) &&
                Objects.equals(isActive, that.isActive) &&
                Objects.equals(role, that.role) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                name,
                surname,
                username,
                isActive,
                role,
                phone);
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", isActive=" + isActive +
                ", role=" + role +
                ", phone='" + phone + '\'' +
                '}';
    }
}
