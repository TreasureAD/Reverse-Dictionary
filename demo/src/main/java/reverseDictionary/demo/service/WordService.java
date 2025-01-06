package reverseDictionary.demo.service;

// Importing necessary dependencies for handling JSON, REST APIs, and Spring functionalities
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reverseDictionary.demo.model.StudySet;
import reverseDictionary.demo.model.Word;
import reverseDictionary.demo.repository.StudySetRepository;
import reverseDictionary.demo.repository.WordRepository;

import java.util.*;

/**
 * Service for managing words and their association with study sets.
 * This class contains business logic for handling words and their relationship with study sets,
 * as well as for searching words using the Datamuse API.
 */
@Service // Marks this class as a service layer in the Spring application.
public class WordService {

    // ====================
    // Instance Variables
    // ====================

    private final WordRepository wordRepository; // Repository for performing database operations on Word entities.
    private final StudySetRepository studySetRepository; // Repository for performing database operations on StudySet entities.
    private final RestTemplate restTemplate; // Used to make HTTP requests to external APIs.

    /**
     * Constructor for dependency injection.
     * Spring automatically injects the required dependencies into this service.
     *
     * @param wordRepository Repository for Word entities.
     * @param studySetRepository Repository for StudySet entities.
     * @param restTemplate RestTemplate for making external API calls.
     */
    @Autowired
    public WordService(WordRepository wordRepository, StudySetRepository studySetRepository, RestTemplate restTemplate) {
        this.wordRepository = wordRepository;
        this.studySetRepository = studySetRepository;
        this.restTemplate = restTemplate;
    }

    // ====================
    // Method: searchWords
    // ====================

    /**
     * Search for words matching a specific definition using the Datamuse API.
     *
     * @param definition The definition or meaning to search for.
     * @return A list of words along with their definitions, formatted as a map.
     */
    public List<Map<String, String>> searchWords(String definition) {
        // API endpoint for the Datamuse API, with placeholders for query parameters.
        String DATAMUSE_API_URL = "https://api.datamuse.com/words?ml={definition}&md=d";

        // Makes a GET request to the Datamuse API and retrieves the response as a JsonNode.
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(DATAMUSE_API_URL, JsonNode.class, definition);

        // Throws an exception if the response body is null or empty, indicating no results.
        if (response.getBody() == null || response.getBody().isEmpty()) {
            throw new RuntimeException("No words found for the given definition.");
        }

        // List to store the formatted words and their definitions.
        List<Map<String, String>> words = new ArrayList<>();
        int idCounter = 1; // Counter to assign unique IDs to each word in the list.

        // Loops through each JSON node (representing a word) in the response body.
        for (JsonNode node : response.getBody()) {
            // Extracts the word text from the JSON node.
            String word = node.get("word").asText();

            // Extracts the first definition if available, otherwise sets a default message.
            String definitionText = node.has("defs") ? node.get("defs").get(0).asText() : "No definition available";

            // Adds the word, its ID, and its definition as a map to the list.
            words.add(Map.of(
                    "id", String.valueOf(idCounter++), // Unique ID for the word.
                    "word", word, // The word itself.
                    "definition", definitionText // The associated definition.
            ));
        }

        return words; // Returns the list of words with their definitions.
    }

    // ============================
    // Method: addWordToStudySet
    // ============================

    /**
     * Adds a word to a specific study set.
     *
     * @param word The word details to add to the study set.
     * @param studySetId The ID of the study set to which the word should be added.
     * @return The word object that was added to the study set.
     */
    public Word addWordToStudySet(Word word, Long studySetId) {
        // Retrieves the study set by ID. If not found, throws a RuntimeException.
        StudySet studySet = studySetRepository.findById(studySetId)
                .orElseThrow(() -> new RuntimeException("Study set not found."));

        // Associates the word with the retrieved study set.
        word.setStudySet(studySet);

        // Saves the word to the database and returns the saved object.
        Word savedWord = wordRepository.save(word);

        return savedWord; // Returns the newly added word.
    }

    // ============================
    // Method: getWordsInStudySet
    // ============================

    /**
     * Retrieves all words associated with a specific study set.
     *
     * @param studySetId The ID of the study set.
     * @return A list of words that belong to the specified study set.
     */
    public List<Word> getWordsInStudySet(Long studySetId) {
        // Retrieves the study set by ID. If not found, throws a RuntimeException.
        StudySet studySet = studySetRepository.findById(studySetId)
                .orElseThrow(() -> new RuntimeException("Study set not found."));

        // Returns a list of words associated with the study set.
        return new ArrayList<>(studySet.getWords());
    }
}
