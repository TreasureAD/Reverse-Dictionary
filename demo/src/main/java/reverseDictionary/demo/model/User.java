package reverseDictionary.demo.model;

// Importing necessary JPA (Jakarta Persistence API) annotations for database mapping
import jakarta.persistence.*;

/**
 * Represents a User entity that maps to the 'user' table in the database.
 * This class defines the structure of the User table, including its fields,
 * constraints, and how it interacts with the database.
 */
@Entity // Marks this class as a JPA Entity, indicating it maps to a database table.
public class User {

    /**
     * The primary key for the 'user' table.
     * This field uniquely identifies each user in the database.
     */
    @Id // Marks this field as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generates unique IDs using the database's auto-increment feature.
    private Long id;

    /**
     * The username of the user.
     * This field must be unique and cannot be null.
     */
    @Column(nullable = false, unique = true) // Maps this field to a database column, enforces non-null and unique constraints.
    private String username;

    /**
     * The password of the user.
     * This field is required and cannot be null.
     */
    @Column(nullable = false) // Maps this field to a non-null database column.
    private String password;

    /**
     * The email address of the user.
     * This field is required and cannot be null.
     */
    @Column(nullable = false) // Maps this field to a non-null database column.
    private String email;

    // ===========================
    // Getters and Setters Section
    // ===========================

    /**
     * Gets the unique identifier (ID) of the user.
     *
     * @return the user's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier (ID) of the user.
     * This is usually managed by the database due to the auto-generation strategy.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the username of the user.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * In a real application, ensure this is **encrypted** before storing it in the database.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the email address of the user.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    // ===========================
    // Additional Notes:
    // ===========================
    /**
     * - The `@Entity` annotation tells JPA to treat this class as a database entity.
     * - The `@Id` annotation specifies the primary key for this entity.
     * - The `@GeneratedValue` with `GenerationType.IDENTITY` ensures that the primary key is auto-incremented.
     * - Each field is mapped to a column in the 'user' table using the `@Column` annotation.
     * - The constraints (e.g., `nullable`, `unique`) ensure data integrity at the database level.
     * - Getters and setters provide controlled access to the private fields, adhering to encapsulation principles.
     */
}
