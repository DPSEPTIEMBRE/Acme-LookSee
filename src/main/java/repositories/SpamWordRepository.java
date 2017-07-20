package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Folder;
import domain.SpamWord;

@Repository
public interface SpamWordRepository extends JpaRepository<SpamWord, Integer> {

}
