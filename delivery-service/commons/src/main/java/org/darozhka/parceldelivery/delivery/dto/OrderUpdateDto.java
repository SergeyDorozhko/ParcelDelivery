package org.darozhka.parceldelivery.delivery.dto;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import org.darozhka.parceldelivery.delivery.domain.Order;

/**
 * @author S.Darozhka
 */
public class OrderUpdateDto {

    private LocalDate deliveryDay;

    private AddressCreateDto pickUpAddress;

    private AddressCreateDto destinationAddress;

    private ContactInfoDto receiverContact;

    private String description;

    public LocalDate getDeliveryDay() {
        return deliveryDay;
    }

    public void setDeliveryDay(LocalDate deliveryDay) {
        this.deliveryDay = deliveryDay;
    }

    public AddressCreateDto getPickUpAddress() {
        return pickUpAddress;
    }

    public void setPickUpAddress(AddressCreateDto pickUpAddress) {
        this.pickUpAddress = pickUpAddress;
    }

    public AddressCreateDto getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(AddressCreateDto destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public ContactInfoDto getReceiverContact() {
        return receiverContact;
    }

    public void setReceiverContact(ContactInfoDto receiverContact) {
        this.receiverContact = receiverContact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderUpdateDto that = (OrderUpdateDto) o;
        return Objects.equals(deliveryDay, that.deliveryDay) &&
                Objects.equals(pickUpAddress, that.pickUpAddress) &&
                Objects.equals(destinationAddress, that.destinationAddress) &&
                Objects.equals(receiverContact, that.receiverContact) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                deliveryDay,
                pickUpAddress,
                destinationAddress,
                receiverContact,
                description);
    }

    @Override
    public String toString() {
        return "OrderCreateDto{" +
                "deliveryDay=" + deliveryDay +
                ", pickUpAddress=" + pickUpAddress +
                ", destinationAddress=" + destinationAddress +
                ", receiverContact=" + receiverContact +
                ", description='" + description + '\'' +
                '}';
    }

    public Order toOrder(Integer orderId) {

        Order order = new Order();
        order.setId(orderId);
        order.setDeliveryDay(getDeliveryDay());
        order.setPickUpAddress(
                Optional.ofNullable(getPickUpAddress())
                        .map(AddressCreateDto::toAddress)
                        .orElse(null));
        order.setDestinationAddress(
                Optional.ofNullable(getDestinationAddress())
                        .map(AddressCreateDto::toAddress)
                        .orElse(null));
        order.setReceiverContact(
                Optional.ofNullable(getReceiverContact())
                        .map(ContactInfoDto::toContactInfo)
                        .orElse(null));
        order.setDescription(getDescription());

        return order;
    }
}
