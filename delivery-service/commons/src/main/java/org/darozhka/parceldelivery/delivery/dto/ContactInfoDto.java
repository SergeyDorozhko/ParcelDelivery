package org.darozhka.parceldelivery.delivery.dto;

import java.util.Objects;

import org.darozhka.parceldelivery.delivery.domain.ContactInfo;

/**
 * @author S.Darozhka
 */
public class ContactInfoDto {

    private String name;

    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        ContactInfoDto that = (ContactInfoDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone);
    }

    @Override
    public String toString() {
        return "ContactInfoDto{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public ContactInfo toContactInfo() {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setPersonName(getName());
        contactInfo.setPhone(getPhone());

        return contactInfo;
    }

    public static ContactInfoDto from(ContactInfo contactInfo) {
        if (Objects.isNull(contactInfo)) {
            return null;
        }

        ContactInfoDto dto = new ContactInfoDto();
        dto.setName(contactInfo.getPersonName());
        dto.setPhone(contactInfo.getPhone());

        return dto;
    }
}
