package contactbook.webapp.client.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;

import contactbook.webapp.client.ContactBookWebapp;
import contactbook.webapp.client.auth.ContactBookAuthServiceAsync;
import contactbook.webapp.shared.Message;

public class TopBar extends HorizontalPanel {
	public TopBar(final ContactBookWebapp webapp, final ContactBookAuthServiceAsync authService) {
		Button logoutButton = new Button(Message.LOGOUT);
		logoutButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				authService.logout(new AsyncCallback<Boolean>() {
					public void onFailure(Throwable arg0) {
						webapp.showError("Error", Message.ERROR_LOGOUT);
					}

					public void onSuccess(Boolean arg0) {
						webapp.logoutSuccess();
					}
				});
			}
		});

		setHorizontalAlignment(ALIGN_RIGHT);
		setSize("100%", "100%");
		add(logoutButton);
	}
}
