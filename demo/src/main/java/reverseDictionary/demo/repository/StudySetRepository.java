package reverseDictionary.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reverseDictionary.demo.model.StudySet;

import java.util.List;

public interface StudySetRepository extends JpaRepository<StudySet, Long> {
    List<StudySet> findByUserId(Long userId); //Derived query
}
