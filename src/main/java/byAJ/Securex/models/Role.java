package byAJ.Securex.models;

import javax.persistence.*;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;



import byAJ.Securex.models.AbstractDomainClass;




@Entity
public class Role extends AbstractDomainClass{

	
	private String role;
	
	
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	@JoinTable
	
	// @JoinTable(name="USER_ROLE", joinColumns=@JoinColumn(name="role_id"),
	// inverseJoinColumns = @joinColumn(name="user_id"))
	
	
	private List<User> usersarr = new ArrayList<>();
	
	private Set<User> users;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<User> getUsersarr() {
		return usersarr;
	}

	public void setUsersarr(List<User> usersarr) {
		this.usersarr = usersarr;
	}

	public Set<User> getUsers() {
		return users;
	}

	
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
	public void addUser(User user) {
		if (!this.usersarr.contains(user)) {
			this.usersarr.add(user);
		}
		
		if(!user.getRoles().contains(this)) {
			user.getRoles().add(this);
		}
	}
		public void removeUser(User user){
			this.usersarr.remove(user);
			user.getRoles().remove(this);
		}
	
	
	
	
}
