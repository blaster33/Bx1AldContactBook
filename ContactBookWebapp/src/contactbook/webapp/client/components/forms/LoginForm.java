package contactbook.webapp.client.components.forms;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

import contactbook.webapp.client.ContactBookWebapp;
import contactbook.webapp.client.auth.ContactBookAuthServiceAsync;
import contactbook.webapp.client.dto.UserDTO;
import contactbook.webapp.shared.Message;

public class LoginForm extends AsyncForm {
	public LoginForm(final ContactBookWebapp webApp, final ContactBookAuthServiceAsync authService) {
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
				authService.login(loginField.getValue(), passwordField.getValue(), new AsyncCallback<UserDTO>() {
					public void onFailure(Throwable arg0) {
						webApp.showError("Login error", Message.SERVER_ERROR);
					}

					public void onSuccess(UserDTO user) {
						if(user != null)
							webApp.loginSuccess(user);
						else
							webApp.showError("Login error", Message.INVALID_LOGIN_INFO);
					}
				});
			}
		});
		
		addStyleName("loginForm");
		addStyleName("center");
	}
}
