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
import contactbook.webapp.client.components.forms.GroupEditForm;
import contactbook.webapp.client.components.util.WidgetPair;
import contactbook.webapp.client.dto.GroupDTO;
import contactbook.webapp.shared.Message;

public class GroupView extends VerticalPanel implements ClickHandler {
	protected ContactBookWebapp webApp;
	protected GroupDTO group;
	protected Button editButton;
	protected Button removeButton;
	
	public GroupView(GroupDTO group, ContactBookWebapp webApp) {
		this.group = group;
		this.webApp = webApp;
		
		add(new WidgetPair(new Label(Message.GROUP_NAME + ": "), new Label(group.getName())));
		
		editButton = new Button(Message.EDIT);
		editButton.addClickHandler(this);
		removeButton = new Button(Message.REMOVE);
		removeButton.addClickHandler(this);

		
		HorizontalPanel panel = new HorizontalPanel();panel.add(editButton);
		panel.add(removeButton);
		add(panel);
	}

	@Override
	public void onClick(ClickEvent event) {
		if(event.getSource() == editButton) {
			webApp.setMain(new GroupEditForm(webApp, group));
		}
		else if(event.getSource() == removeButton) {
			boolean removeContacts = Window.confirm("Do you want to remove the contacts in the group " + group.getName() + "?");
			String message = removeContacts? Message.CONFIRM_REMOVE_GROUP_AND_CONTACTS:Message.CONFIRM_REMOVE_GROUP;
			
			if(Window.confirm(message)) {
				webApp.getBusinessService().removeGroup(group, removeContacts, new AsyncCallback<Boolean>() {
					public void onSuccess(Boolean result) {
						webApp.showInfo(Message.GROUP, Message.REMOVE_GROUP_SUCCESS);
						webApp.setMain(new HTML(""));
					}
					
					public void onFailure(Throwable caught) {
						webApp.showError(Message.ERROR, Message.REMOVE_GROUP_FAILURE);
					}
				});
			}
		}
	}
}