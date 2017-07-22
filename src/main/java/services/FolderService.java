package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Folder;
import domain.MailMessage;
import repositories.FolderRepository;

@Service
@Transactional
public class FolderService {

	//Repositories

	@Autowired
	private FolderRepository folderRepository;

	//Services
	
	//Constructor
	
	public FolderService() {
		super();
	}

	//CRUD Methods
	
	public Folder create(){
		Folder folder=new Folder();
		
		folder.setFolderName(new String());
		folder.setMessages(new ArrayList<MailMessage>());
		
		return folder;
	}

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

	public List<Folder> createDefaultFolders(){
		List<Folder> folders=new ArrayList<Folder>();
		
		Folder inbox= create();
		inbox.setFolderName("Inbox");

		Folder outbox= create();
		inbox.setFolderName("Outbox");
		
		Folder trashbox= create();
		inbox.setFolderName("Trashbox");
		
		Folder spambox= create();
		inbox.setFolderName("Spambox");
		
		folders.add(inbox);
		folders.add(outbox);
		folders.add(trashbox);
		folders.add(spambox);
		
		return folders;
	}


}
