package org.darozhka.parceldelivery.delivery.dto;

import java.util.Objects;

import org.darozhka.parceldelivery.delivery.domain.Address;

/**
 * @author S.Darozhka
 */
public class AddressDto {

    private Integer id;

    private String city;

    private String street;

    private Integer building;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getBuilding() {
        return building;
    }

    public void setBuilding(Integer building) {
        this.building = building;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AddressDto that = (AddressDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(city, that.city) &&
                Objects.equals(street, that.street) &&
                Objects.equals(building, that.building);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                city,
                street,
                building);
    }

    @Override
    public String toString() {
        return "AddressDto{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                '}';
    }

    public static AddressDto from(Address address) {
        if (Objects.isNull(address)) {
            return null;
        }

        AddressDto dto = new AddressDto();
        dto.setCity(address.getCity());
        dto.setStreet(address.getStreet());
        dto.setBuilding(address.getBuilding());

        return dto;
    }
}
