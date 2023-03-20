package org.darozhka.parceldelivery.iam.dto;

import java.util.Objects;

import org.darozhka.parceldelivery.iam.domain.User;

/**
 * @author S.Darozhka
 */
public class UserRegistrationDto {

    private String name;

    private String surname;

    private String username;

    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        UserRegistrationDto that = (UserRegistrationDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                name,
                surname,
                username,
                password,
                phone);
    }

    @Override
    public String toString() {
        return "UserCreateDto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", password=" + password +
                ", phone='" + phone + '\'' +
                '}';
    }

    public User toUser() {
        User user = new User();
        user.setName(this.name);
        user.setSurname(this.surname);
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setActive(Boolean.TRUE);
        user.setPhone(this.phone);

        return user;
    }
}
