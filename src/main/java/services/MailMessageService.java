package services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Folder;
import domain.MailMessage;
import domain.Priority;
import repositories.MailMessageRepository;

@Service
@Transactional
public class MailMessageService {

	//Repositories

	@Autowired
	private MailMessageRepository mailMessageRepository;

	//Services
	
	@Autowired
	private AdministratorService administratorService;
	
	//Constructor
	
	public MailMessageService() {
		super();
	}


	//CRUD Methods
	
	public MailMessage create() {
		MailMessage message= new MailMessage();
		
		message.setBody(new String());
		
		Priority priority= new Priority();
		priority.setValue("NEUTRAL");
		
		message.setPriority(priority);
		message.setRecipient(administratorService.create());
		message.setSender(administratorService.create());
		message.setSent(new Date());

		
		return message;
	}

	public List<MailMessage> findAll() {
		return mailMessageRepository.findAll();
	}

	public MailMessage findOne(Integer arg0) {
		return mailMessageRepository.findOne(arg0);
	}

	public <S extends MailMessage> List<S> save(Iterable<S> entities) {
		return mailMessageRepository.save(entities);
	}

	public <S extends MailMessage> S save(S arg0) {
		return mailMessageRepository.save(arg0);
	}

	
	//Other Methods
	
	public List<Folder> messagesByFolder(int folder_id) {
		return mailMessageRepository.messagesByFolder(folder_id);
	}
}
