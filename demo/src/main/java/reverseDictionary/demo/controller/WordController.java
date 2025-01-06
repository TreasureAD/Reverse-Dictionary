package reverseDictionary.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reverseDictionary.demo.model.Word;
import reverseDictionary.demo.service.WordService;

import java.util.List;
import java.util.Map;

/**
 * Controller for managing words.
 */
@RestController
@RequestMapping("/api/words")
public class WordController {

    private final WordService wordService;

    @Autowired
    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    /**
     * Search for words by definition.
     *
     * @param definition The definition to search for.
     * @return A list of words matching the definition.
     */
    @GetMapping("/search")
    public ResponseEntity<List<Map<String, String>>> searchWords(@RequestParam String definition) {
        return ResponseEntity.ok(wordService.searchWords(definition));
    }

    /**
     * Add a word to a specific study set.
     *
     * @param studySetId The ID of the study set.
     * @param word       The word details to add.
     * @return The added word.
     */
    @PostMapping("/add-to-studyset")
    public ResponseEntity<Word> addWordToStudySet(
            @RequestParam Long studySetId,
            @RequestBody Word word) {
        Word addedWord = wordService.addWordToStudySet(word, studySetId);
        return ResponseEntity.ok(addedWord);
    }

    /**
     * Retrieve all words in a specific study set.
     *
     * @param studySetId The ID of the study set.
     * @return A list of words in the study set.
     */
    @GetMapping("/studyset/{studySetId}")
    public ResponseEntity<List<Word>> getWordsInStudySet(@PathVariable Long studySetId) {
        return ResponseEntity.ok(wordService.getWordsInStudySet(studySetId));
    }
}
