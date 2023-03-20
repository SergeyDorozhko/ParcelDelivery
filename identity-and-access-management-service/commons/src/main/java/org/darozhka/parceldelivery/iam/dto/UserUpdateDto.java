package org.darozhka.parceldelivery.iam.dto;

import java.util.Objects;

import org.darozhka.parceldelivery.iam.domain.User;

/**
 * @author S.Darozhka
 */
public class UserUpdateDto {

    private String name;

    private String surname;

    private String username;

    private Boolean isActive;

    private String phone;

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
        UserUpdateDto that = (UserUpdateDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(username, that.username) &&
                Objects.equals(isActive, that.isActive) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                name,
                surname,
                username,
                isActive,
                phone);
    }

    @Override
    public String toString() {
        return "UserUpdateDto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", isActive=" + isActive +
                ", phone='" + phone + '\'' +
                '}';
    }

    public User toUser(Integer id) {
        User user = new User();

        user.setId(id);
        user.setName(this.name);
        user.setSurname(this.surname);
        user.setUsername(this.username);
        user.setActive(this.isActive);
        user.setPhone(this.phone);

        return user;
    }
}
