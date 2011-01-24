package contactbook.webapp.server.business;

import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import contactbook.model.Contact;
import contactbook.model.Group;
import contactbook.model.User;
import contactbook.service.ContactServiceRemote;
import contactbook.webapp.client.business.ContactBookBusinessService;
import contactbook.webapp.client.dto.*;
import contactbook.webapp.server.DtoUtils;

public class ContactBookBusinessServiceImpl extends RemoteServiceServlet
implements ContactBookBusinessService {
	private static final long serialVersionUID = 7739432976910489833L;

	private ContactServiceRemote contactService = null;

	public ContactBookBusinessServiceImpl() {
		try {
			Context ctx = new InitialContext();
			contactService = (ContactServiceRemote) ctx.lookup(ContactServiceRemote.JNDI);
		}
		catch(NamingException e) {
			System.err.println(e.getMessage());
			System.err.println(e.getExplanation());
			e.printStackTrace();
		}
	}

	@Override
	public List<ContactDTO> getContacts(UserDTO userDto) {
		List<ContactDTO> dtos = new Vector<ContactDTO>();
		User user = contactService.getUserByName(userDto.getLoginName());
		List<Contact> contacts = contactService.getContactsByUser(user);

		for(Contact c: contacts)
			dtos.add(DtoUtils.dtoFromContact(c, DtoUtils.dtoFromGroup(userDto, c.getGroup()), userDto));

		return dtos;
	}

	@Override
	public List<ContactDTO> getContacts(GroupDTO groupDto) {
		User user = contactService.getUserByName(groupDto.getUser().getLoginName());
		List<Contact> contacts = contactService.getContactsByGroup(DtoUtils.groupFromDTO(groupDto, user));
		List<ContactDTO> dtos = new Vector<ContactDTO>();

		for(Contact c: contacts)
			dtos.add(DtoUtils.dtoFromContact(c, groupDto, groupDto.getUser()));

		return dtos;
	}

	@Override
	public List<GroupDTO> getGroups(UserDTO userDto) {
		List<GroupDTO> dtos = new Vector<GroupDTO>();
		User user = contactService.getUserByName(userDto.getLoginName());
		List<Group> groups = contactService.getGroupsByUser(user);

		for(Group g: groups)
			dtos.add(DtoUtils.dtoFromGroup(userDto, g));

		return dtos;
	}

	@Override
	public boolean addOrUpdateGroup(GroupDTO group) {
		User u = contactService.getUserByName(group.getUser().getLoginName());
		Group g = DtoUtils.groupFromDTO(group, u);
		if(g.getId() > 0)
			g = contactService.updateGroup(g);
		else
			g = contactService.addGroup(g);

		return (g != null && g.getId() > 0);
	}

	@Override
	public boolean addOrUpdateContact(ContactDTO dto) {
		if(dto.getGroup() == null)
			return false;

		User user = contactService.getUserByName(dto.getUser().getLoginName());
		Group group = contactService.getGroupByName(dto.getGroup().getName(), user);
		Contact contact = DtoUtils.contactFromDTO(dto, user, group);
		if(contact.getId() > 0)
			contact = contactService.updateContact(contact);
		else
			contact = contactService.addContact(contact);

		return(contact != null && contact.getId() > 0);
	}

	@Override
	public boolean removeGroup(GroupDTO dto, boolean removeContacts) {
		try {
			User user = contactService.getUserByName(dto.getUser().getLoginName());
			Group group = contactService.getGroupByName(dto.getName(), user);
			contactService.removeGroup(group, removeContacts);
		} catch(Exception e) {
			return false;
		}

		return true;
	}

	@Override
	public boolean removeContact(ContactDTO dto) {
		try {
			User user = contactService.getUserByName(dto.getUser().getLoginName());
			// TODO check identity
			contactService.removeContact(DtoUtils.contactFromDTO(dto, user, DtoUtils.groupFromDTO(dto.getGroup(), user)));
		} catch(Exception e) {
			return false;
		}
		return true;
	}
}