package contactbook.webapp.client.components.widgets;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;

import contactbook.webapp.client.ContactBookWebapp;
import contactbook.webapp.client.business.ContactBookBusinessServiceAsync;
import contactbook.webapp.client.dto.GroupDTO;
import contactbook.webapp.shared.Message;

public class GroupListBox extends ListBox {
	protected List<GroupDTO> groupDTOs;
	
	public GroupListBox(final ContactBookWebapp webApp) {
		ContactBookBusinessServiceAsync businessService = webApp.getBusinessService();
		businessService.getGroups(webApp.getCurrentUser(), new AsyncCallback<List<GroupDTO>>() {
			public void onFailure(Throwable arg0) {
				webApp.showError(Message.ERROR, Message.ERROR_LOADING_GROUP_LIST);
			}

			public void onSuccess(List<GroupDTO> list) {
				groupDTOs = list;

				for(GroupDTO g: list) {
					addItem(g.getName());
				}
			}
		});
		
		setVisibleItemCount(1);
	}
	
	public void setSelectedGroup(GroupDTO group) {
		for(int i = 0; i < getItemCount(); i++) {
			String item = getItemText(i);
			if(item.equals(group.getName())) {
				setSelectedIndex(i);
				break;
			}
		}
	}
	
	public GroupDTO getSelectedGroup() {
		return groupDTOs.get(getSelectedIndex());
	}
}
