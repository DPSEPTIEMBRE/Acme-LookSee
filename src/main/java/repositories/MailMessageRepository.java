package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Folder;
import domain.MailMessage;

@Repository
public interface MailMessageRepository extends
JpaRepository<MailMessage, Integer> {

	//Mensajes de una carpeta
	@Query("select f.messages from Folder f where f.id=?1 ")
	List<Folder> messagesByFolder(int folder_id);
	
	@Query("select c from Actor c where c.email = ?1 or c.userAccount.username = ?2")
	Actor selectActorByMail(String mail, String username);
	
	@Query("select c from Actor c where c.userAccount.username = ?1")
	Actor selectSelf(String username);
}
