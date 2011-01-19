package contactbook.webapp.server;

import contactbook.model.Contact;
import contactbook.model.Group;
import contactbook.model.User;
import contactbook.webapp.client.dto.ContactDTO;
import contactbook.webapp.client.dto.GroupDTO;
import contactbook.webapp.client.dto.UserDTO;

public class DtoUtils {
	public static ContactDTO dtoFromContact(Contact c, GroupDTO g, UserDTO u) {
		ContactDTO dto = new ContactDTO();
		
		dto.setId(c.getId());
		dto.setLastName(c.getLastName());
		dto.setFirstName(c.getFirstName());
		dto.setHomePhone(c.getHomePhone());
		dto.setCellPhone(c.getCellPhone());
		dto.setEmail(c.getEmail());
		dto.setAddress1(c.getAddress1());
		dto.setAddress2(c.getAddress2());
		dto.setZipCode(c.getZipCode());
		dto.setCity(c.getCity());
		dto.setState(c.getState());
		dto.setCountry(c.getCountry());
		dto.setDateOfBirth(c.getDateOfBirth());
		dto.setGroup(g);
		dto.setUser(u);
		
		return dto;
	}
	
	public static Contact contactFromDTO(ContactDTO dto, User user, Group group) {
		Contact c = new Contact(user);
		
		c.setId(dto.getId());
		c.setLastName(dto.getLastName());
		c.setFirstName(dto.getFirstName());
		c.setHomePhone(dto.getHomePhone());
		c.setCellPhone(dto.getCellPhone());
		c.setEmail(dto.getEmail());
		c.setAddress1(dto.getAddress1());
		c.setAddress2(dto.getAddress2());
		c.setZipCode(dto.getZipCode());
		c.setCity(dto.getCity());
		c.setState(dto.getState());
		c.setCountry(dto.getCountry());
		c.setDateOfBirth(dto.getDateOfBirth());
		c.setGroup(group);
		
		return c;
	}
	
	public static GroupDTO dtoFromGroup(UserDTO user, Group g) {
		if(g == null)
			return null;
		GroupDTO dto = new GroupDTO();
		dto.setId(g.getId());
		dto.setName(g.getName());
		dto.setUser(user);
		return dto;
	}
	
	public static Group groupFromDTO(GroupDTO dto, User user) {
		Group g = new Group(user, dto.getName());
		g.setId(dto.getId());
		return g;
	}
	
	public static UserDTO dtoFromUser(User u) {
		UserDTO dto = new UserDTO();
		
		dto.setId(u.getId());
		dto.setAdmin(u.isAdmin());
		dto.setLoginName(u.getLoginName());
		dto.setEmail(u.getEmail());
		dto.setLastLogin(u.getLastLogin());
		dto.setDefaultGroup(dtoFromGroup(dto, u.getDefaultGroup()));
		
		return dto;
	}
	
	public static User userFromDTO(UserDTO dto) {
		User u = new User();
		u.setAdmin(dto.isAdmin());
		u.setDefaultGroup(groupFromDTO(dto.getDefaultGroup(), u));
		u.setEmail(dto.getEmail());
		u.setId(dto.getId());
		u.setLastLogin(dto.getLastLogin());
		u.setLoginName(dto.getLoginName());
		return u;
	}
}
