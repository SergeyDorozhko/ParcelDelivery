package org.darozhka.parceldelivery.delivery.dto;

import java.util.Objects;

import org.darozhka.parceldelivery.delivery.domain.Address;

/**
 * @author S.Darozhka
 */
public class AddressUpdateDto {

    private String city;

    private String street;

    private Integer building;

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
        AddressUpdateDto that = (AddressUpdateDto) o;
        return Objects.equals(city, that.city) &&
                Objects.equals(street, that.street) &&
                Objects.equals(building, that.building);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, building);
    }

    @Override
    public String toString() {
        return "AddressUpdateDto{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building=" + building +
                '}';
    }

    public Address toAddress(Integer id) {
        Address address = new Address();
        address.setId(id);
        address.setCity(getCity());
        address.setStreet(getStreet());
        address.setBuilding(getBuilding());

        return address;
    }
}
