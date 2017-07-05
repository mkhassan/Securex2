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
	
	
	private List<User> users = new ArrayList<>();
	
	private Set<User> usersset;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<User> getUsersarr() {
		return users;
	}

	public void setUsersarr(List<User> users) {
		this.users = users;
	}

	public Set<User> getUsers() {
		return usersset;
	}

	
	public void setUsers(Set<User> usersset) {
		this.usersset = usersset;
	}
	
	
	public void addUser(User user) {
		if (!this.users.contains(user)) {
			this.users.add(user);
		}
		
		if(!user.getRoles().contains(this)) {
			user.getRoles().add(this);
		}
	}
		public void removeUser(User user){
			this.users.remove(user);
			user.getRoles().remove(this);
		}
	
	
	
	
}
