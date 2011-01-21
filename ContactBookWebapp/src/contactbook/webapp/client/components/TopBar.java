package contactbook.webapp.client.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;

import contactbook.webapp.client.ContactBookWebapp;
import contactbook.webapp.client.components.forms.ContactEditForm;
import contactbook.webapp.client.components.forms.GroupEditForm;
import contactbook.webapp.shared.Message;

public class TopBar extends HorizontalPanel implements ClickHandler {
	protected ContactBookWebapp webApp;
	
	protected Button logoutButton;
	protected Button newGroupButton;
	protected Button newContactButton;
	
	public TopBar(final ContactBookWebapp webApp) {
		this.webApp = webApp;
		
		getElement().setId("topBar");
		logoutButton = new Button(Message.LOGOUT);
		logoutButton.addClickHandler(this);
		
		newGroupButton = new Button(Message.GROUP_NEW);
		newGroupButton.addClickHandler(this);
		
		newContactButton = new Button(Message.CONTACT_NEW);
		newContactButton.addClickHandler(this);

		setHorizontalAlignment(ALIGN_RIGHT);
		setSize("100%", "100%");
		add(newGroupButton);
		add(newContactButton);
		add(logoutButton);
	}

	@Override
	public void onClick(ClickEvent event) {
		if(event.getSource() == logoutButton)
			doLogout();
		else if(event.getSource() == newGroupButton)
			doNewGroup();
		else if(event.getSource() == newContactButton)
			doNewContact();
	}
	
	protected void doLogout() {
		webApp.getAuthService().logout(new AsyncCallback<Boolean>() {
			public void onFailure(Throwable arg0) {
				webApp.showError("Error", Message.ERROR_LOGOUT);
			}

			public void onSuccess(Boolean arg0) {
				webApp.logoutSuccess();
			}
		});
	}
	
	protected void doNewGroup() {
		webApp.setMain(new GroupEditForm(webApp));
	}
	
	protected void doNewContact() {
		webApp.setMain(new ContactEditForm(webApp));
	}
}