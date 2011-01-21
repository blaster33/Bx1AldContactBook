package contactbook.webapp.client.components;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.TabLayoutPanel;

import contactbook.webapp.client.ContactBookWebapp;
import contactbook.webapp.client.business.ContactBookBusinessServiceAsync;
import contactbook.webapp.client.components.widgets.LinksContactList;
import contactbook.webapp.client.components.widgets.TreeContactList;
import contactbook.webapp.shared.Message;

public class LeftPanel extends TabLayoutPanel {
	protected ContactBookBusinessServiceAsync businessService;
	protected ContactBookWebapp webApp;

	protected LinksContactList linksList;
	protected TreeContactList treeList;

	public LeftPanel(ContactBookWebapp webApp, ContactBookBusinessServiceAsync businessService) {
		super(25, Unit.PX);
		getElement().setId("leftPanel");
		setSize("100%", "100%");
		
		this.businessService = businessService;
		this.webApp = webApp;

		linksList = new LinksContactList(webApp);
		linksList.setSize("100%", "100%");
		add(linksList, Message.ALL);
		
		treeList = new TreeContactList(webApp);
		treeList.setSize("100%", "100%");
		add(treeList, Message.BY_GROUP);
		refresh();
	}
	
	public void refresh() {
		linksList.refresh();
		treeList.refresh();
	}
}
