package byAJ.Securex.services;

import java.util.List;
import java.util.ArrayList;

import byAJ.Securex.models.User;
import byAJ.Securex.repositories.UserRepository;
import byAJ.Securex.services.UserService;

import byAJ.Securex.services.security.EncryptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Profile("springdatajpa")

public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	private EncryptionService encryptionService;
	
	@Autowired
	public void setEncryptionService(EncryptionService encryptionService){
		this.encryptionService = encryptionService;
	}
	@Override
	public List<?> listAll() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add); // lambdas
	
		return users;
	}

	@Override
	public User getById(Integer id) {
	
		return userRepository.findOne(id);
	}

	@Override
	public User saveOrUpdate(User domainObject) {
		if(domainObject.getPassword() != null) {
			domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));
		}
		return userRepository.save(domainObject);
	}

	@Override
	@Transactional
	
	public void delete(Integer id) {
		userRepository.delete(id);
		
	}

	@Override
	public User findByUsername(String username) {

		return userRepository.findByUsername(username);
	}
	
	

}
