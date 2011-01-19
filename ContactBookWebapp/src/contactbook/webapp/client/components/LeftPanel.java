package contactbook.webapp.client.components;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

import contactbook.webapp.client.ContactBookWebapp;
import contactbook.webapp.client.business.ContactBookBusinessServiceAsync;
import contactbook.webapp.client.dto.ContactDTO;
import contactbook.webapp.client.dto.UserDTO;
import contactbook.webapp.shared.Message;

public class LeftPanel extends VerticalPanel {
	protected ContactBookBusinessServiceAsync businessService;
	protected UserDTO user;
	protected ContactBookWebapp webApp;

	public LeftPanel(UserDTO user, ContactBookBusinessServiceAsync businessService,
			ContactBookWebapp webApp) {
		
		getElement().setId("leftPanel");
		add(new Button("test"));
		setSize("100%", "100%");
		
		this.businessService = businessService;
		this.webApp = webApp;
		setUser(user);
		refresh();
	}
	
	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	public void refresh() {
		clear();
		businessService.getContacts(user, new AsyncCallback<List<ContactDTO>>() {
			public void onSuccess(List<ContactDTO> contacts) {
				for(ContactDTO c: contacts) {
					add(new HTML(c.getFirstName() + " " + c.getLastName() + "<br />"));
				}
			}
			
			public void onFailure(Throwable arg0) {
				webApp.showError(Message.ERROR, Message.ERROR_LOADING_CONTACTS +
						"<br />" + arg0.getMessage());
			}
		});
	}
}
