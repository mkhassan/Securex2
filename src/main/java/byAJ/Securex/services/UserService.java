package byAJ.Securex.services;

import byAJ.Securex.models.User;

public interface UserService extends CRUDService<User> {
	
	User findByUsername(String username);

}
