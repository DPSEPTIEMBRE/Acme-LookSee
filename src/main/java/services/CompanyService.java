package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Company;
import repositories.CompanyRepository;

@Service
@Transactional
public class CompanyService {

	//Repositories

	@Autowired
	private CompanyRepository companyRepository;

	//Services

	//CRUD Methods

	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	public Company findOne(Integer arg0) {
		return companyRepository.findOne(arg0);
	}

	public <S extends Company> List<S> save(Iterable<S> entities) {
		return companyRepository.save(entities);
	}

	public <S extends Company> S save(S arg0) {
		return companyRepository.save(arg0);
	}
	
	//Others Methods
	
	public Company companyByOffer(int offer_id) {
		return companyRepository.companyByOffer(offer_id);
	}

	public Company companies() {
		return companyRepository.companies();
	}

	public List<Company> orderByNumOffers() {
		return companyRepository.orderByNumOffers();
	}

	public Double avgNumberOfferByCompany() {
		return companyRepository.avgNumberOfferByCompany();
	}

	public List<Company> companyMaxOffers() {
		return companyRepository.companyMaxOffers();
	}

}
