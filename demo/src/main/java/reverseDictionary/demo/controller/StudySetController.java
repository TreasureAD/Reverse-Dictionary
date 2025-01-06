package reverseDictionary.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reverseDictionary.demo.model.StudySet;
import reverseDictionary.demo.service.StudySetService;

import java.util.List;

/**
 * Controller for managing Study Sets.
 */
@RestController
@RequestMapping("/api/studysets")
public class StudySetController {

    private final StudySetService studySetService;

    @Autowired
    public StudySetController(StudySetService studySetService) {
        this.studySetService = studySetService;
    }

    /**
     * Create a new Study Set.
     *
     * @param userId   The ID of the user who owns the study set.
     * @param studySet The Study Set object to create.
     * @return The created Study Set.
     */
    @PostMapping("/create")
    public ResponseEntity<StudySet> createStudySet(@RequestParam Long userId, @RequestBody StudySet studySet) {
        return ResponseEntity.ok(studySetService.createStudySet(studySet, userId));
    }

    /**
     * Retrieve all Study Sets for a specific user.
     *
     * @param userId The ID of the user.
     * @return A list of Study Sets owned by the user.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<StudySet>> getStudySetsByUserId(@PathVariable Long userId) {
        List<StudySet> studySets = studySetService.getStudySetsByUserId(userId);
        if (studySets.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studySets);
    }

    /**
     * Retrieve a specific Study Set by ID.
     *
     * @param id The ID of the Study Set.
     * @return The requested Study Set.
     */
    @GetMapping("/{id}")
    public ResponseEntity<StudySet> getStudySetById(@PathVariable Long id) {
        return ResponseEntity.ok(studySetService.getStudySetById(id));
    }

    /**
     * Update an existing Study Set.
     *
     * @param id            The ID of the Study Set to update.
     * @param updatedStudySet The updated Study Set object.
     * @return The updated Study Set.
     */
    @PutMapping("/{id}")
    public ResponseEntity<StudySet> updateStudySet(@PathVariable Long id, @RequestBody StudySet updatedStudySet) {
        StudySet studySet = studySetService.updateStudySet(id, updatedStudySet);
        return ResponseEntity.ok(studySet);
    }

    /**
     * Delete a specific Study Set.
     *
     * @param id The ID of the Study Set to delete.
     * @return A no-content response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudySet(@PathVariable Long id) {
        studySetService.deleteStudySet(id);
        return ResponseEntity.noContent().build();
    }
}
