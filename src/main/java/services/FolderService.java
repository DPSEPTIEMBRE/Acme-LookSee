package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Folder;
import repositories.FolderRepository;

@Service
@Transactional
public class FolderService {

	//Repositories

	@Autowired
	private FolderRepository folderRepository;

	//Services

	//CRUD Methods
	
	public List<Folder> findAll() {
		return folderRepository.findAll();
	}

	public Folder findOne(Integer arg0) {
		return folderRepository.findOne(arg0);
	}

	public <S extends Folder> List<S> save(Iterable<S> entities) {
		return folderRepository.save(entities);
	}

	public <S extends Folder> S save(S arg0) {
		return folderRepository.save(arg0);
	}
	
	//Others Methods
	
	public List<Folder> foldersByActor(int actor_id) {
		return folderRepository.foldersByActor(actor_id);
	}


}
