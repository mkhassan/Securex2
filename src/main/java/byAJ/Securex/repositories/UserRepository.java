package byAJ.Securex.repositories;

import byAJ.Securex.models.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer>{
	
	User findByUsername(String username);

}
