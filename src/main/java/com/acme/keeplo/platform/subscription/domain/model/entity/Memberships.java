package com.acme.keeplo.platform.subscription.domain.model.entity;

import com.acme.keeplo.platform.shared.domain.model.entities.AuditableModel;
import com.acme.keeplo.platform.subscription.domain.exceptions.NegativeMembershipPriceException;
import com.acme.keeplo.platform.subscription.domain.exceptions.UnknownMembershipTypeException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
/**
 * Memberships Entity
 *
 * Represents a subscription membership plan in the system.
 * Each membership has a unique name, a price, and a description.
 * This entity extends AuditableModel to include creation and update timestamps.
 */
@Getter
@Setter
@Entity
public class Memberships extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    private String description;

    /**
     * Protected constructor for JPA.
     */
    protected Memberships() {
        this.name = "";
        this.price = 0.0f;
        this.description = "";
    }

    /**
     * Creates a new membership with the given values.
     *
     * @param name the name of the membership (must not be null or blank)
     * @param price the price of the membership (must not be negative)
     * @param description the description of the membership (must not be null or blank)
     */
    public Memberships(String name, float price, String description) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Membership name cannot be null or empty.");
        }
        if (price < 0) {
            throw new NegativeMembershipPriceException();
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Membership description cannot be null or empty.");
        }
        this.name = name;
        this.price = price;
        this.description = description;
    }

    /**
     * Updates this membership with new values.
     *
     * @param name the new name (must not be null or blank)
     * @param price the new price (must not be negative)
     * @param description the new description (must not be null or blank)
     */
    public void update(String name, float price, String description) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Membership name cannot be null or empty.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Membership price cannot be negative.");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Membership description cannot be null or empty.");
        }
        this.name = name;
        this.price = price;
        this.description = description;
    }

    /**
     * Checks if the membership is free (i.e., price is 0).
     *
     * @return true if the membership is free, false otherwise
     */
    public boolean isFree() {
        return price == 0;
    }

    /**
     * Returns the maximum number of collections allowed for this membership type.
     *
     * @return maximum number of collections allowed
     * @throws UnknownMembershipTypeException if the membership type is unknown
     */
    public int getMaxCollectionsAllowed() {
        if (this.isFree()) return 2;
        if ("Plus".equalsIgnoreCase(this.name)) return 5;
        if ("Infinite".equalsIgnoreCase(this.name)) return 10;
        throw new UnknownMembershipTypeException(this.name);
    }
}
