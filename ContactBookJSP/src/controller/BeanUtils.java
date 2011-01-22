package controller;

import bean.ContactBean;
import bean.GroupBean;
import bean.UserBean;
import contactbook.model.Contact;
import contactbook.model.Group;
import contactbook.model.User;

public class BeanUtils {
	public static ContactBean beanFromContact(Contact c, GroupBean g, UserBean u) {
		ContactBean bean = new ContactBean();
		
		bean.setId(c.getId());
		bean.setLastName(c.getLastName());
		bean.setFirstName(c.getFirstName());
		bean.setHomePhone(c.getHomePhone());
		bean.setCellPhone(c.getCellPhone());
		bean.setEmail(c.getEmail());
		bean.setAddress1(c.getAddress1());
		bean.setAddress2(c.getAddress2());
		bean.setZipCode(c.getZipCode());
		bean.setCity(c.getCity());
		bean.setState(c.getState());
		bean.setCountry(c.getCountry());
		bean.setDateOfBirth(c.getDateOfBirth());
		bean.setGroup(g);
		bean.setUser(u);
		
		return bean;
	}
	
	public static Contact contactFromBean(ContactBean bean, User user, Group group) {
		Contact c = new Contact(user);
		
		c.setId(bean.getId());
		c.setLastName(bean.getLastName());
		c.setFirstName(bean.getFirstName());
		c.setHomePhone(bean.getHomePhone());
		c.setCellPhone(bean.getCellPhone());
		c.setEmail(bean.getEmail());
		c.setAddress1(bean.getAddress1());
		c.setAddress2(bean.getAddress2());
		c.setZipCode(bean.getZipCode());
		c.setCity(bean.getCity());
		c.setState(bean.getState());
		c.setCountry(bean.getCountry());
		c.setDateOfBirth(bean.getDateOfBirth());
		c.setGroup(group);
		
		return c;
	}
	
	public static GroupBean beanFromGroup(UserBean user, Group g) {
		if(g == null)
			return null;
		GroupBean bean = new GroupBean();
		bean.setId(g.getId());
		bean.setName(g.getName());
		bean.setUser(user);
		return bean;
	}
	
	public static Group groupFromBean(GroupBean bean, User user) {
		Group g = new Group(user, bean.getName());
		g.setId(bean.getId());
		return g;
	}
	
	public static UserBean beanFromUser(User u) {
		UserBean bean = new UserBean();
		
		bean.setId(u.getId());
		bean.setAdmin(u.isAdmin());
		bean.setLoginName(u.getLoginName());
		bean.setEmail(u.getEmail());
		bean.setLastLogin(u.getLastLogin());
		bean.setDefaultGroup(beanFromGroup(bean, u.getDefaultGroup()));
		
		return bean;
	}
	
	public static User userFromBean(UserBean bean) {
		User u = new User();
		u.setAdmin(bean.isAdmin());
		u.setDefaultGroup(groupFromBean(bean.getDefaultGroup(), u));
		u.setEmail(bean.getEmail());
		u.setId(bean.getId());
		u.setLastLogin(bean.getLastLogin());
		u.setLoginName(bean.getLoginName());
		return u;
	}
}
