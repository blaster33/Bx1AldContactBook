package contactbook.webapp.client.components;

import com.google.gwt.user.client.ui.SimplePanel;

import contactbook.webapp.client.ContactBookWebapp;

public class MainPanel extends SimplePanel {
	
	public MainPanel(ContactBookWebapp webApp) {
		getElement().setId("mainPanel");
		setSize("100%", "100%");
	}
}
