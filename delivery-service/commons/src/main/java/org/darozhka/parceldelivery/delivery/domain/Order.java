package org.darozhka.parceldelivery.delivery.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author S.Darozhka
 */
@Entity
@Table(name = "delivery_order")
public class Order {

    @Id
    @SequenceGenerator(
            name = "delivery_order_id_seq",
            sequenceName = "delivery_order_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "delivery_order_id_seq"
    )
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courier_id", referencedColumnName = "id")
    private User courier;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    private LocalDate deliveryDay;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pick_up_address_id", referencedColumnName = "id")
    private Address pickUpAddress;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "destination_address_id", referencedColumnName = "id")
    private Address destinationAddress;

    @Embedded
    private ContactInfo receiverContact;

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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getCourier() {
        return courier;
    }

    public void setCourier(User courier) {
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

    public Address getPickUpAddress() {
        return pickUpAddress;
    }

    public void setPickUpAddress(Address pickUpAddress) {
        this.pickUpAddress = pickUpAddress;
    }

    public Address getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(Address destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public ContactInfo getReceiverContact() {
        return receiverContact;
    }

    public void setReceiverContact(ContactInfo receiverContact) {
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
        Order that = (Order) o;
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
        return "Order{" +
                "id=" + id +
                ", owner=" + owner +
                ", courier=" + courier +
                ", status=" + status +
                ", deliveryDay=" + deliveryDay +
                ", pickUpAddress=" + pickUpAddress +
                ", deliveryAddress=" + destinationAddress +
                ", receiverContact=" + receiverContact +
                ", description='" + description + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", lastModifiedAt=" + lastModifiedAt +
                '}';
    }
}
