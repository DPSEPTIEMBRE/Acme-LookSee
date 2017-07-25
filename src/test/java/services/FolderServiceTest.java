package services;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import domain.Administrator;
import domain.Folder;

import utilities.AbstractTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional

public class FolderServiceTest extends AbstractTest {
	
	//Service under test ---------------------
	@Autowired
	private FolderService folderService;
			
	//Supporting services --------------------
	@Autowired
	private AdministratorService adminService;
	
	//Tests ----------------------------------

	@Test
	public void testCreate() {
		System.out.println("========================");
		System.out.println("== CREATE FOLDER ==");
		System.out.println("========================");
		authenticate("admin1");
		Folder folder = folderService.create();
		Assert.notNull(folder);
		authenticate(null);
		
	}
	
	@Test
	public void testFindOne() {
		System.out.println("========================");
		System.out.println("== FIND ONE FOLDER ==");
		System.out.println("========================");
		Folder folder;
		folder = folderService.findOne(115);
		Assert.notNull(folder);
		System.out.println("NOMBRE CARPETA= "+ folder.getFolderName()+" ID= " + folder.getId());
	}
	
	@Test
	public void testSave() {
		System.out.println("========================");
		System.out.println("== SAVE FOLDER ==");
		System.out.println("========================");
		authenticate("admin1");
		Administrator a = adminService.findByPrincipal();
		Assert.notNull(a);	
		System.out.println("Nº de folders del admin "+ a.getactorName() +": "+a.getFolders().size());
		Folder f = folderService.create();
		f.setFolderName("Carpeta");
		Assert.notNull(f);
		a.getFolders().add(f);	
		folderService.save(f);
		System.out.println("DESPUÉS DE GUARDAR");
		System.out.println("Nº de folders del admin "+a.getactorName()+": "+a.getFolders().size());
	
		authenticate(null);
	}
			
			
}
