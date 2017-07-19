package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Folder;
import domain.SpamWord;
import repositories.SpamWordRepository;

@Service
@Transactional
public class SpamWordService {

	//Repositories

	@Autowired
	private  SpamWordRepository  spamWordRepository;

	//Services

	//CRUD Methods
	
	public List<SpamWord> findAll() {
		return spamWordRepository.findAll();
	}

	public SpamWord findOne(Integer arg0) {
		return spamWordRepository.findOne(arg0);
	}

	public <S extends SpamWord> List<S> save(Iterable<S> entities) {
		return spamWordRepository.save(entities);
	}

	public <S extends SpamWord> S save(S arg0) {
		return spamWordRepository.save(arg0);
	}
	
	//Others Methods
	
	public List<Folder> listSpamWords() {
		return spamWordRepository.listSpamWords();
	}


}
