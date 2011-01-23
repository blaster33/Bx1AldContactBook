package contactbook.webapp.client.components.widgets;

import java.util.List;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

import contactbook.webapp.client.ContactBookWebapp;
import contactbook.webapp.client.components.views.ContactView;
import contactbook.webapp.client.components.views.GroupView;
import contactbook.webapp.client.dto.ContactDTO;
import contactbook.webapp.client.dto.GroupDTO;
import contactbook.webapp.shared.Message;

public class TreeContactList extends Tree implements SelectionHandler<TreeItem> {
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
		
		addSelectionHandler(this);
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
								webApp.setMain(new ContactView(c, webApp));
						}
					});
					node.addItem(link);
				}
			}
		});
	}

	@Override
	public void onSelection(SelectionEvent<TreeItem> event) {
		TreeItem item = event.getSelectedItem();
		if(item.getParentItem() ==  null) {
			GroupDTO dto = new GroupDTO();
			dto.setName(item.getText());
			dto.setUser(webApp.getCurrentUser());
			webApp.setMain(new GroupView(dto, webApp));
		}
	}
}
