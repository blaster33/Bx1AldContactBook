package contactbook.webapp.client.components;

import java.util.List;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

import contactbook.model.Contact;

public class LeftPanel extends VerticalPanel {
	protected List<Contact> contacts;
	
	public LeftPanel() {
		getElement().setId("leftPanel");
		add(new Button("test"));
		setSize("100%", "100%");
	}
	
	public LeftPanel(List<Contact> contacts) {
		this();
		setContacts(contacts);
		refresh();
	}
	
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
	public List<Contact> getContacts() {
		return contacts;
	}
	
	public void refresh() {
		clear();
		
		if(contacts == null)
			return;
		
		for(Contact u: contacts) {
			add(new HTML(u.getFirstName() + " " + u.getLastName() + "<br />"));
		}
	}
}
