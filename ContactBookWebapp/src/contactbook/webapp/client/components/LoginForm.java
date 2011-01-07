package contactbook.webapp.client.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import contactbook.webapp.client.ContactBookWebapp;
import contactbook.webapp.client.auth.ContactBookAuthServiceAsync;
import contactbook.webapp.shared.Message;

public class LoginForm extends VerticalPanel {
	private static LoginForm instance = null;
	
	private LoginForm(final ContactBookWebapp webApp, final ContactBookAuthServiceAsync authService) {
		final TextBox loginField = new TextBox();
		final TextBox passwordField = new PasswordTextBox();
		Button loginButton = new Button("Login");
		HTML registerLink = new HTML("<a href=\"#\">Don't have an account yet?");
		
		registerLink.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				webApp.displayRegistrationForm();
			}
		});
		
		add(new HTML("Username:"));
		add(loginField);
		add(new HTML("Password:"));
		add(passwordField);
		add(loginButton);
		add(registerLink);
		
		loginButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				authService.login(loginField.getValue(), passwordField.getValue(), new AsyncCallback<Boolean>() {
					public void onFailure(Throwable arg0) {
						webApp.showError("Login error", Message.SERVER_ERROR);
					}

					public void onSuccess(Boolean res) {
						if(res)
							webApp.loginSuccess();
						else
							webApp.showError("Login error", Message.INVALID_LOGIN_INFO);
					}
				});
			}
		});
		
		addStyleName("loginForm");
		addStyleName("center");
	}
	
	public static LoginForm getInstance(ContactBookWebapp webApp, ContactBookAuthServiceAsync authService) {
		if(instance == null)
			instance = new LoginForm(webApp, authService);
		
		return instance;
	}
}
