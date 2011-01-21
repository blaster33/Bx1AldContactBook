package contactbook.webapp.client.components.widgets;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

import contactbook.webapp.client.ContactBookWebapp;
import contactbook.webapp.client.components.views.ContactView;
import contactbook.webapp.client.dto.ContactDTO;
import contactbook.webapp.shared.Message;

public class LinksContactList extends VerticalPanel {
	protected ContactBookWebapp webApp;
	
	private VerticalPanel vPanel;
	
	public LinksContactList(ContactBookWebapp webApp) {
		this.webApp = webApp;
		vPanel = new VerticalPanel();
		add(vPanel);
	}
	
	public void refresh() {
		vPanel.clear();
		
		webApp.getBusinessService().getContacts(webApp.getCurrentUser(), new AsyncCallback<List<ContactDTO>>() {
			public void onSuccess(List<ContactDTO> contacts) {
				for(final ContactDTO c: contacts) {
					HTML link = new HTML("<a href=\"#\">" + c.getFirstName() + " " + c.getLastName() + "</a>");
					link.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent arg0) {
								webApp.setMain(new ContactView(c));
						}
					});
					vPanel.add(link);
				}
			}
			
			public void onFailure(Throwable arg0) {
				webApp.showError(Message.ERROR, Message.ERROR_LOADING_CONTACTS +
						"<br />" + arg0.getMessage());
			}
		});
	}
}
