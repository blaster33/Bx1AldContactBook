package contactbook.webapp.client.components.views;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import contactbook.webapp.client.ContactBookWebapp;
import contactbook.webapp.client.components.forms.ContactEditForm;
import contactbook.webapp.client.components.util.WidgetPair;
import contactbook.webapp.client.dto.ContactDTO;
import contactbook.webapp.shared.Message;

public class ContactView extends VerticalPanel implements ClickHandler {
	protected ContactBookWebapp webApp;
	protected ContactDTO contact;
	protected Button editButton;
	protected Button removeButton;

	public ContactView(ContactDTO contact, ContactBookWebapp webApp) {
		this.webApp = webApp;
		this.contact = contact;

		editButton = new Button(Message.EDIT);
		removeButton = new Button(Message.REMOVE);

		HorizontalPanel panel = new HorizontalPanel();
		panel.add(editButton);
		panel.add(removeButton);

		add(panel);
		add(new HTML("<h1>" + contact.getFirstName() + " " + contact.getLastName() + "</h1>"));
		
		add(new WidgetPair(new Label(Message.CONTACT_HOME_PHONE + ": "), new Label(contact.getHomePhone())));
		add(new WidgetPair(new Label(Message.CONTACT_CELL_PHONE + ": "), new Label(contact.getCellPhone())));
		add(new WidgetPair(new Label(Message.CONTACT_EMAIL + ": "), new Label(contact.getEmail())));
		add(new WidgetPair(new Label(Message.CONTACT_ADDRESS + ": "), new Label(contact.getAddress1())));
		add(new Label(contact.getAddress2()));
		add(new WidgetPair(new Label(Message.CONTACT_ZIP_CODE + ": "), new Label(contact.getZipCode())));
		add(new WidgetPair(new Label(Message.CONTACT_CITY + ": "), new Label(contact.getCity())));
		add(new WidgetPair(new Label(Message.CONTACT_STATE + ": "), new Label(contact.getState())));
		add(new WidgetPair(new Label(Message.CONTACT_COUNTRY + ": "), new Label(contact.getCountry())));
		add(new WidgetPair(new Label(Message.CONTACT_STATE + ": "), new Label(contact.getState())));

		editButton.addClickHandler(this);
		removeButton.addClickHandler(this);
	}

	@Override
	public void onClick(ClickEvent event) {
		if(event.getSource() == editButton) {
			webApp.setMain(new ContactEditForm(webApp, contact));
		}
		else if(event.getSource() == removeButton) {
			if(Window.confirm(Message.CONFIRM_REMOVE_CONTACT)) {
				webApp.getBusinessService().removeContact(contact, new AsyncCallback<Boolean>() {
					public void onSuccess(Boolean result) {
						webApp.showInfo(Message.CONTACT, Message.REMOVE_CONTACT_SUCCESSFUL);
						webApp.setMain(new HTML(""));
					}

					public void onFailure(Throwable caught) {
						webApp.showError(Message.CONTACT, Message.REMOVE_CONTACT_FAILURE);
					}
				});
			}
		}
	}
}
