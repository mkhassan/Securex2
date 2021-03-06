package byAJ.Securex.services.security;

import byAJ.Securex.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import byAJ.Securex.models.User;
import byAJ.Securex.services.UserService;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{
	
	
	private UserService userService;
	private Converter<User, UserDetails> userUserDetailsConverter;
	
@Autowired

public void setUserService(UserService userService) {
	
	this.userService = userService;
}

@Autowired
@Qualifier(value = "userToUserDetails") 

public void setUserUserDetailsConverter(Converter<User, UserDetails> userUserDetailsConverter) {
	
	this.userUserDetailsConverter = userUserDetailsConverter;
}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return userUserDetailsConverter.convert(userService.findByUsername(username));
	}
	
	

	

}
