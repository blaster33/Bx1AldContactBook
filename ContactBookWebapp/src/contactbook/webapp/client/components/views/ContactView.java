package contactbook.webapp.client.components.views;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import contactbook.webapp.client.components.util.WidgetPair;
import contactbook.webapp.client.dto.ContactDTO;
import contactbook.webapp.shared.Message;

public class ContactView extends VerticalPanel {
	public ContactView(ContactDTO contact) {
		add(new WidgetPair(new Label(Message.CONTACT_FIRST_NAME), new Label(contact.getFirstName())));
		add(new WidgetPair(new Label(Message.CONTACT_LAST_NAME), new Label(contact.getLastName())));
	}
}
