package contactbook.webapp.client;

import contactbook.webapp.client.auth.ContactBookAuthService;
import contactbook.webapp.client.auth.ContactBookAuthServiceAsync;
import contactbook.webapp.client.business.ContactBookBusinessService;
import contactbook.webapp.client.business.ContactBookBusinessServiceAsync;
import contactbook.webapp.client.components.LeftPanel;
import contactbook.webapp.client.components.forms.LoginForm;
import contactbook.webapp.client.components.MainPanel;
import contactbook.webapp.client.components.forms.RegistrationForm;
import contactbook.webapp.client.components.TopBar;
import contactbook.webapp.client.dto.*;
import contactbook.webapp.shared.Message;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ContactBookWebapp implements EntryPoint {
	private final ContactBookAuthServiceAsync authService = GWT.create(ContactBookAuthService.class);
	private final ContactBookBusinessServiceAsync businessService = GWT.create(ContactBookBusinessService.class);
	
	private UserDTO user;
	protected FlowPanel flowPanel;
	
	protected DialogBox dialogBox;
	protected HTML errorLabel;
	protected Button closeButton;
	
	protected LeftPanel leftPanel;
	protected MainPanel mainPanel;
	protected DockLayoutPanel dockLayoutPanel;
	
	public ContactBookWebapp() {
		flowPanel = new FlowPanel();
		flowPanel.setStyleName("center");
	}
	
	protected void initDockLayoutPanel() {
		if(dockLayoutPanel != null)
			return;
		if(leftPanel == null)
			leftPanel = new LeftPanel(this, businessService);
		
		RootPanel.get().clear();
		dockLayoutPanel = new DockLayoutPanel(Unit.PX);
		
		dockLayoutPanel.addNorth(new TopBar(this), 25);
		dockLayoutPanel.addWest(leftPanel, 225);
		mainPanel = new MainPanel(this);
		dockLayoutPanel.add(mainPanel);
	}

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// Create the popup dialog box
		dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		dialogBox.setModal(true);
		closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		errorLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(errorLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);
		
		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});
		
		authService.isLoggedIn(new AsyncCallback<Boolean>() {
			public void onFailure(Throwable arg0) {
				showError("Error", Message.SERVER_ERROR);
			}

			public void onSuccess(Boolean res) {
				if(res) {
					displayMainUI();
				}
				else {
					displayLoginForm();
				}
			}
		});
	}
	
	public void loginSuccess(UserDTO user) {
		this.user = user;
		showInfo("Info", Message.LOGIN_SUCCESSFUL);
		displayMainUI();
	}
	
	public void logoutSuccess() {
		displayLoginForm();
	}
	
	public void registrationSuccess() {
		showInfo("Registration", Message.ACCOUNT_CREATE_SUCCESSFUL);
		displayLoginForm();
	}
	
	public void displayLoginForm() {
		RootPanel.get().clear();
		flowPanel.clear();
		RootPanel.get().add(flowPanel);
		flowPanel.add(new LoginForm(this, authService));
	}
	
	public void displayRegistrationForm() {
		RootPanel.get().clear();
		flowPanel.clear();
		RootPanel.get().add(flowPanel);
		flowPanel.add(RegistrationForm.getInstance(this, authService));
	}
	
	public void displayMainUI() {
		initDockLayoutPanel();
		RootPanel.get().clear();
		RootLayoutPanel.get().add(dockLayoutPanel);
	}
	
	public void showInfo(String title, String message) {
		dialogBox.setText(title);
		errorLabel.setText(message);
		errorLabel.setStyleName("infoText");
		closeButton.setVisible(false);
		dialogBox.center();
		new Timer() {
			public void run() {
				dialogBox.hide();
				closeButton.setVisible(true);
			}
		}.schedule(2000);
	}
	
	public void showError(String title, String message) {
		dialogBox.setText(title);
		errorLabel.setText(message);
		errorLabel.setStyleName("serverResponseLabelError");
		dialogBox.center();
	}
	
	public void setMain(Widget widget) {
		mainPanel.clear();
		mainPanel.add(widget);
	}

	public ContactBookAuthServiceAsync getAuthService() {
		return authService;
	}

	public ContactBookBusinessServiceAsync getBusinessService() {
		return businessService;
	}
	
	public UserDTO getCurrentUser() {
		return user;
	}
	
	public void refreshLeft() {
		leftPanel.refresh();
	}
}