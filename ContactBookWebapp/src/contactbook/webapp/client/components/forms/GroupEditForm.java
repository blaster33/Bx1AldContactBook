package contactbook.webapp.client.components.forms;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import contactbook.webapp.client.ContactBookWebapp;
import contactbook.webapp.client.business.ContactBookBusinessServiceAsync;
import contactbook.webapp.client.components.util.WidgetPair;
import contactbook.webapp.client.dto.GroupDTO;
import contactbook.webapp.shared.Message;

public class GroupEditForm extends AsyncForm {
	public GroupEditForm(final ContactBookWebapp webApp) {
		this(webApp, null);
	}
	
	public GroupEditForm(final ContactBookWebapp webApp, final GroupDTO group) {
		final TextBox nameField = new TextBox();
		final Label nameLabel = new Label("");
		
		if(group != null)
			nameField.setText(group.getName());
		nameLabel.setStyleName("formError");
		
		add(new Label(Message.GROUP_NAME + ":"));
		add(new WidgetPair(nameField, nameLabel));
		
		Button submitButton = new Button(Message.SAVE);
		add(submitButton);
		
		submitButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String groupName = nameField.getText();
				
				nameLabel.setText("");
				if(groupName.equals("")) {
					nameLabel.setText(Message.EMPTY_GROUP_NAME);
					return;
				}
				
				ContactBookBusinessServiceAsync contactService = webApp.getBusinessService();
				GroupDTO dto = new GroupDTO();
				dto.setName(groupName);
				dto.setUser(webApp.getCurrentUser());
				
				contactService.addOrUpdateGroup(dto, new AsyncCallback<Boolean>() {
					public void onSuccess(Boolean res) {
						webApp.refreshLeft();
						formValidated();
					}
					
					public void onFailure(Throwable arg0) {
						webApp.showError(Message.ERROR, Message.ERROR_SAVING_GROUP);
						formValidated();
					}
				});
			}
		});
	}
}