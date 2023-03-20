package org.darozhka.parceldelivery.delivery.domain;

import java.util.Objects;

import javax.persistence.Embeddable;

/**
 * @author S.Darozhka
 */
@Embeddable
public class ContactInfo {

    private String personName;

    private String phone;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
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
        ContactInfo that = (ContactInfo) o;
        return Objects.equals(personName, that.personName) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personName, phone);
    }

    @Override
    public String toString() {
        return "ContactInfo{" +
                "personName='" + personName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
