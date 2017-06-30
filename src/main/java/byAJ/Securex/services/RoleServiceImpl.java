package byAJ.Securex.services;

import java.util.List;
import java.util.ArrayList;

import byAJ.Securex.models.Role;
import byAJ.Securex.repositories.RoleRepository;
import byAJ.Securex.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;



@Service
@Profile("springdatajpa")
public class RoleServiceImpl implements RoleService {
	
	
	private RoleRepository roleRepository;
	
	@Autowired
	
	public void setRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	

	@Override
	public List<?> listAll() {
		List<Role> roles = new ArrayList<>();
		roleRepository.findAll().forEach(roles::add);
		
		return roles;
	}

	@Override
	public Role getById(Integer id) {
	
		return roleRepository.findOne(id);
	}

	@Override
	public Role saveOrUpdate(Role domainObject) {

		return roleRepository.save(domainObject);
	}

	@Override
	public void delete(Integer id) {
		roleRepository.delete(id);
		
	}

}
