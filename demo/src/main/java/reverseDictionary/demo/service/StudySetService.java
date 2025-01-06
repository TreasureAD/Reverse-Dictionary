package reverseDictionary.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reverseDictionary.demo.model.StudySet;
import reverseDictionary.demo.model.User;
import reverseDictionary.demo.repository.StudySetRepository;
import reverseDictionary.demo.repository.UserRepository;

import java.util.List;

/**
 * Service for managing Study Sets.
 */
@Service
public class StudySetService {

    private final StudySetRepository studySetRepository;
    private final UserRepository userRepository;

    @Autowired
    public StudySetService(StudySetRepository studySetRepository, UserRepository userRepository) {
        this.studySetRepository = studySetRepository;
        this.userRepository = userRepository;
    }

    /**
     * Create a new Study Set for a user.
     *
     * @param studySet The Study Set object to create.
     * @param userId   The ID of the user who owns the study set.
     * @return The created Study Set.
     */
    public StudySet createStudySet(StudySet studySet, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        studySet.setUser(user);
        return studySetRepository.save(studySet);
    }

    /**
     * Retrieve all Study Sets owned by a specific user.
     *
     * @param userId The ID of the user.
     * @return A list of Study Sets.
     */
    public List<StudySet> getStudySetsByUserId(Long userId) {
        return studySetRepository.findByUserId(userId);
    }

    /**
     * Retrieve a Study Set by its ID.
     *
     * @param id The ID of the Study Set.
     * @return The requested Study Set.
     */
    public StudySet getStudySetById(Long id) {
        return studySetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Study Set not found with ID: " + id));
    }

    /**
     * Update an existing Study Set.
     *
     * @param id              The ID of the Study Set.
     * @param updatedStudySet The updated Study Set object.
     * @return The updated Study Set.
     */
    public StudySet updateStudySet(Long id, StudySet updatedStudySet) {
        return studySetRepository.findById(id)
                .map(studySet -> {
                    studySet.setName(updatedStudySet.getName());
                    studySet.setDescription(updatedStudySet.getDescription());
                    return studySetRepository.save(studySet);
                })
                .orElseThrow(() -> new RuntimeException("Study Set not found with ID: " + id));
    }

    /**
     * Delete a Study Set by its ID.
     *
     * @param id The ID of the Study Set.
     */
    public void deleteStudySet(Long id) {
        if (!studySetRepository.existsById(id)) {
            throw new RuntimeException("Study Set not found with ID: " + id);
        }
        studySetRepository.deleteById(id);
    }
}
