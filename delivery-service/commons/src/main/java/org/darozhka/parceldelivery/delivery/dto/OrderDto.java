package org.darozhka.parceldelivery.delivery.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import org.darozhka.parceldelivery.delivery.domain.Order;
import org.darozhka.parceldelivery.delivery.domain.OrderStatus;

/**
 * @author S.Darozhka
 */
public class OrderDto {

    private Integer id;

    private UserDto owner;

    private UserDto courier;

    private OrderStatus status;

    private LocalDate deliveryDay;

    private AddressDto pickUpAddress;

    private AddressDto destinationAddress;

    private ContactInfoDto receiverContact;

    private String description;

    private String createdBy;

    private LocalDateTime createdAt;

    private String lastModifiedBy;

    private LocalDateTime lastModifiedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDto getOwner() {
        return owner;
    }

    public void setOwner(UserDto owner) {
        this.owner = owner;
    }

    public UserDto getCourier() {
        return courier;
    }

    public void setCourier(UserDto courier) {
        this.courier = courier;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDate getDeliveryDay() {
        return deliveryDay;
    }

    public void setDeliveryDay(LocalDate deliveryDay) {
        this.deliveryDay = deliveryDay;
    }

    public AddressDto getPickUpAddress() {
        return pickUpAddress;
    }

    public void setPickUpAddress(AddressDto pickUpAddress) {
        this.pickUpAddress = pickUpAddress;
    }

    public AddressDto getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(AddressDto destinationAddress) {
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderDto that = (OrderDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(owner, that.owner) &&
                Objects.equals(courier, that.courier) &&
                status == that.status &&
                Objects.equals(deliveryDay, that.deliveryDay) &&
                Objects.equals(pickUpAddress, that.pickUpAddress) &&
                Objects.equals(destinationAddress, that.destinationAddress) &&
                Objects.equals(receiverContact, that.receiverContact) &&
                Objects.equals(description, that.description) &&
                Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(lastModifiedBy, that.lastModifiedBy) &&
                Objects.equals(lastModifiedAt, that.lastModifiedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                owner,
                courier,
                status,
                deliveryDay,
                pickUpAddress,
                destinationAddress,
                receiverContact,
                description,
                createdBy,
                createdAt,
                lastModifiedBy,
                lastModifiedAt);
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", owner=" + owner +
                ", courier=" + courier +
                ", status=" + status +
                ", deliveryDay=" + deliveryDay +
                ", pickUpAddress=" + pickUpAddress +
                ", destinationAddress=" + destinationAddress +
                ", receiverContact=" + receiverContact +
                ", description='" + description + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", lastModifiedAt=" + lastModifiedAt +
                '}';
    }

    public static OrderDto from(Order order) {
        if (Objects.isNull(order)) {
            return null;
        }

        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setOwner(UserDto.from(order.getOwner()));
        dto.setCourier(UserDto.from(order.getCourier()));
        dto.setStatus(order.getStatus());
        dto.setDeliveryDay(order.getDeliveryDay());
        dto.setPickUpAddress(AddressDto.from(order.getPickUpAddress()));
        dto.setDestinationAddress(AddressDto.from(order.getDestinationAddress()));
        dto.setReceiverContact(ContactInfoDto.from(order.getReceiverContact()));
        dto.setDescription(order.getDescription());
        dto.setCreatedBy(order.getCreatedBy());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setLastModifiedBy(order.getLastModifiedBy());
        dto.setLastModifiedAt(order.getLastModifiedAt());

        return dto;
    }
}
