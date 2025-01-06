package reverseDictionary.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reverseDictionary.demo.model.User;
import reverseDictionary.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing User operations.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Create a new user.
     *
     * @param user User object containing user details.
     * @return Saved User object.
     */
    public User createUser(User user) {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        return userRepository.save(user);
    }

    /**
     * Retrieve all users.
     *
     * @return List of all users.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieve a user by ID.
     *
     * @param id User ID.
     * @return Optional User object.
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Update user details.
     *
     * @param id          User ID.
     * @param updatedUser Updated User object.
     * @return Updated User object.
     */
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setEmail(updatedUser.getEmail());
                    user.setPassword(updatedUser.getPassword());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * Delete a user by ID.
     *
     * @param id User ID.
     */
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }
}
