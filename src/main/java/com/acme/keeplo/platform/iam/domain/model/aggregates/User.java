package com.acme.keeplo.platform.iam.domain.model.aggregates;

import com.acme.keeplo.platform.iam.domain.model.entities.Role;
import com.acme.keeplo.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User Aggregate Root for IAM.
 *
 * This entity represents the core user identity within the platform,
 * combining personal details with authentication and authorization capabilities.
 * It is an aggregate root responsible for managing its own lifecycle and relationships,
 * including roles and associated subscriptions.
 *
 * @see AuditableAbstractAggregateRoot
 */
@Entity
@Getter
@Setter
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User extends AuditableAbstractAggregateRoot<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    @Size(max = 120)
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String profilePicture;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id", unique = true)
    private Subscription subscription;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    protected User() {
        this.roles = new HashSet<>();
    }

    /**
     * Constructor for creating a new User based on common details.
     * This constructor does NOT set roles; roles should be added explicitly.
     *
     * @param email The user's email, acting as their unique identifier (username).
     * @param password The user's HASHEd password.
     * @param name The user's full name.
     * @param profilePicture The URL or path to the user's profile picture.
     */
    public User(String email, String password, String name, String profilePicture) {
        this();
        this.email = email;
        this.password = password;
        this.name = name;
        this.profilePicture = profilePicture;
    }

    /**
     * Constructor for creating a new User with initial roles.
     *
     * @param email The user's email.
     * @param password The user's HASHEd password.
     * @param name The user's full name.
     * @param profilePicture The URL or path to the user's profile picture.
     * @param roles A list of initial roles to assign to the user.
     */
    public User(String email, String password, String name, String profilePicture, List<Role> roles) {
        this(email, password, name, profilePicture);
        addRoles(roles);
    }


    /**
     * Adds a single role to the user's set of roles.
     *
     * @param role The Role entity to add.
     * @return The updated User instance (for method chaining).
     */
    public User addRole(Role role) {
        this.roles.add(role);
        return this;
    }

    /**
     * Adds a collectionId of roles to the user's set of roles.
     * @param roles The List of Role entities to add.
     * @return The updated User instance (for method chaining).
     */
    public User addRoles(List<Role> roles) {
        var validatedRoleSet = Role.validateRoleSet(roles);
        this.roles.addAll(validatedRoleSet);
        return this;
    }
}