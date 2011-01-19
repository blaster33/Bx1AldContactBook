package contactbook.webapp.client;

import contactbook.webapp.client.auth.ContactBookAuthService;
import contactbook.webapp.client.auth.ContactBookAuthServiceAsync;
import contactbook.webapp.client.components.LeftPanel;
import contactbook.webapp.client.components.LoginForm;
import contactbook.webapp.client.components.MainPanel;
import contactbook.webapp.client.components.RegistrationForm;
import contactbook.webapp.client.components.TopBar;
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

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ContactBookWebapp implements EntryPoint {
	//private final GreetingServiceAsync authService = GWT.create(GreetingService.class);
	private final ContactBookAuthServiceAsync authService = GWT.create(ContactBookAuthService.class);
	
	FlowPanel flowPanel;
	
	DialogBox dialogBox;
	HTML errorLabel;
	Button closeButton;
	
	public ContactBookWebapp() {
		flowPanel = new FlowPanel();
		flowPanel.setStyleName("center");
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
	
	public void loginSuccess() {
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
		RootPanel.get().add(flowPanel);
		flowPanel.add(new LoginForm(this, authService));
	}
	
	public void displayRegistrationForm() {
		RootPanel.get().clear();
		RootPanel.get().add(flowPanel);
		flowPanel.add(RegistrationForm.getInstance(this, authService));
	}
	
	public void displayMainUI() {
		RootPanel.get().clear();
		DockLayoutPanel dlp = new DockLayoutPanel(Unit.PX);
		
		dlp.addNorth(new TopBar(this, authService), 30);
		dlp.addWest(new LeftPanel(), 150);
		dlp.add(new MainPanel());
		
		RootLayoutPanel.get().add(dlp);
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
}