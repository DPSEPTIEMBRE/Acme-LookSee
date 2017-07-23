package services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
		Assert.notNull(arg0);
		
		return mailMessageRepository.findOne(arg0);
	}

	public List<MailMessage> save(List<MailMessage> entities) {
		Assert.notNull(entities);
		Assert.noNullElements(entities.toArray());
		
		return mailMessageRepository.save(entities);
	}

	public MailMessage save(MailMessage arg0) {
		Assert.notNull(arg0);
		
		return mailMessageRepository.save(arg0);
	}

	
	//Other Methods
	
	public List<Folder> messagesByFolder(int folder_id) {
		Assert.notNull(folder_id);
		
		return mailMessageRepository.messagesByFolder(folder_id);
	}
}
