package reverseDictionary.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reverseDictionary.demo.model.Word;

public interface WordRepository extends JpaRepository<Word, Long> {
}
