package contactbook.webapp.client.components.widgets;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

import contactbook.webapp.client.ContactBookWebapp;
import contactbook.webapp.client.components.views.ContactView;
import contactbook.webapp.client.dto.ContactDTO;
import contactbook.webapp.client.dto.GroupDTO;
import contactbook.webapp.shared.Message;

public class TreeContactList extends Tree {
	protected ContactBookWebapp webApp;

	public TreeContactList(ContactBookWebapp webApp) {
		this.webApp = webApp;
	}

	public void refresh() {
		clear();

		webApp.getBusinessService().getGroups(webApp.getCurrentUser(), new AsyncCallback<List<GroupDTO>>() {
			public void onSuccess(List<GroupDTO> groups) {
				for(GroupDTO g: groups) {
					TreeItem groupNode = new TreeItem(g.getName());
					addItem(groupNode);
					populateContacts(groupNode, g);
				}
			}

			public void onFailure(Throwable arg0) {
				webApp.showError(Message.ERROR, Message.ERROR_LOADING_CONTACTS +
						"<br />" + arg0.getMessage());
			}
		});
	}
	
	protected void populateContacts(final TreeItem node, GroupDTO group) {
		webApp.getBusinessService().getContacts(group, new AsyncCallback<List<ContactDTO>>() {
			public void onFailure(Throwable arg0) {
				webApp.showError(Message.ERROR, Message.ERROR_LOADING_CONTACTS);
			}

			public void onSuccess(List<ContactDTO> contacts) {
				for(final ContactDTO c: contacts) {
					HTML link = new HTML("<a href=\"#\">" + c.getFirstName() + " " + c.getLastName() + "</a>");
					link.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent arg0) {
								webApp.setMain(new ContactView(c));
						}
					});
					node.addItem(link);
				}
			}
		});
	}
}
