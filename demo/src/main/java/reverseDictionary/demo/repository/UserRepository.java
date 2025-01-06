package reverseDictionary.demo.repository;

// Importing the User entity from the model package
import reverseDictionary.demo.model.User;

// Importing JpaRepository for CRUD operations
import org.springframework.data.jpa.repository.JpaRepository;

// Importing Optional to handle potential null results gracefully
import java.util.Optional;

/**
 * UserRepository interface serves as the data access layer for User entities.
 * It extends JpaRepository to provide CRUD operations and query support.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a User by their username.
     * This method allows retrieving a user based on their unique username.
     *
     * @param username The username to search for in the database.
     * @return An Optional containing the User if found, or empty if not found.
     *
     * Example Query Generated:
     * SELECT * FROM user WHERE username = ?;
     */
    Optional<User> findByUsername(String username);
}
