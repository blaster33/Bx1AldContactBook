package contactbook.webapp.client;

import contactbook.webapp.client.auth.ContactBookAuthService;
import contactbook.webapp.client.auth.ContactBookAuthServiceAsync;
import contactbook.webapp.client.components.LoginForm;
import contactbook.webapp.client.components.RegistrationForm;
import contactbook.webapp.shared.FieldVerifier;
import contactbook.webapp.shared.Message;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
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
		RootPanel.get("content").add(flowPanel);
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
					flowPanel.add(LoginForm.getInstance(ContactBookWebapp.this, authService));
				}
			}
		});
	}
	
	public void loginSuccess() {
		RootPanel.get("content").add(new HTML("Logged in"));
	}
	
	public void registrationSuccess() {
		showInfo("Registration", Message.ACCOUNT_CREATE_SUCCESSFUL);
		displayLoginForm();
	}
	
	public void displayLoginForm() {
		RootPanel.get("content").clear();
		RootPanel.get("content").add(LoginForm.getInstance(this, authService));
	}
	
	public void displayRegistrationForm() {
		RootPanel.get("content").clear();
		RootPanel.get("content").add(RegistrationForm.getInstance(this, authService));
	}
	
	public void displayMainUI() {
		
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