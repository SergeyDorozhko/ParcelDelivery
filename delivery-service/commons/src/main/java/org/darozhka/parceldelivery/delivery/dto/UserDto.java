package org.darozhka.parceldelivery.delivery.dto;

import java.util.Objects;
import java.util.Optional;

import org.darozhka.parceldelivery.delivery.domain.User;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author S.Darozhka
 */
public class UserDto {

    private Integer id;

    private String name;

    private String surname;

    private String username;

    private Boolean isActive;

    private String role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
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
        UserDto that = (UserDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(username, that.username) &&
                Objects.equals(isActive, that.isActive) &&
                role == that.role &&
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
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", isActive=" + isActive +
                ", role=" + role +
                ", phone='" + phone + '\'' +
                '}';
    }

    public static UserDto from(User user) {
        if (Objects.isNull(user)) {
            return null;
        }

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setUsername(user.getUsername());
        dto.setActive(user.getActive());
        dto.setRole(
                Optional.ofNullable(user.getRole())
                        .map(GrantedAuthority::getAuthority)
                        .orElse(null));
        dto.setPhone(user.getPhone());

        return dto;
    }

}
