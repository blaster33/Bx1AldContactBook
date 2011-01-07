package contactbook.webapp.client.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import contactbook.webapp.client.ContactBookWebapp;
import contactbook.webapp.client.auth.ContactBookAuthServiceAsync;
import contactbook.webapp.shared.FieldVerifier;
import contactbook.webapp.shared.Message;

public class RegistrationForm extends VerticalPanel {
	private static RegistrationForm instance = null;
	
	private boolean loginAvailable = false;
	
	private RegistrationForm(final ContactBookWebapp webApp, final ContactBookAuthServiceAsync authService) {
		final TextBox loginField = new TextBox();
		final TextBox passwordField = new PasswordTextBox();
		final TextBox confirmPasswordField = new PasswordTextBox();
		final TextBox emailField = new TextBox();
		final TextBox confirmEmailField = new TextBox();

		final Label loginError = new Label("");
		final Label passwordError = new Label("");
		final Label confirmPasswordError = new Label("");
		final Label emailError = new Label("");
		final Label confirmEmailError = new Label("");

		loginError.setStyleName("formError");
		passwordError.setStyleName("formError");
		confirmPasswordError.setStyleName("formError");
		emailError.setStyleName("formError");
		confirmEmailError.setStyleName("formError");

		Button loginButton = new Button("Register an account");

		add(new HTML("Username:"));
		add(new Pair(loginField, loginError));
		add(new HTML("Password:"));
		add(new Pair(passwordField, passwordError));
		add(new HTML("Confirm password:"));
		add(new Pair(confirmPasswordField, confirmPasswordError));
		add(new HTML("Email:"));
		add(new Pair(emailField, emailError));
		add(new HTML("Confirm email:"));
		add(new Pair(confirmEmailField,confirmEmailError));
		add(loginButton);
		
		HTML loginLink = new HTML("<a href=\"#\">Already have an account?");
		
		loginLink.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				webApp.displayLoginForm();
			}
		});
		add(loginLink);
		
		// Keyboard handler
		loginField.addKeyUpHandler(new KeyUpHandler() {
			public void onKeyUp(KeyUpEvent event) {
				authService.usernameAvailable(loginField.getValue(), new AsyncCallback<Boolean>() {
					public void onFailure(Throwable arg0) {
						// Just ignore the error, another one will occur
						// with form validation
					}

					public void onSuccess(Boolean available) {
						if(available) {
							loginError.setText(Message.LOGIN_AVAILABLE);
							loginError.setStyleName("formValid");
						}
						else {
							loginError.setText(Message.LOGIN_TAKEN);
							loginError.setStyleName("formError");
						}
						loginAvailable = available;
					}
					
				});
			}
		});

		// Click handler
		loginButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				boolean ok = true;
				if(loginError.getText().equals(Message.EMPTY_LOGIN))
					loginError.setText("");
				passwordError.setText("");
				confirmPasswordError.setText("");
				emailError.setText("");
				confirmEmailError.setText("");
				
				String login = loginField.getValue();
				String password = passwordField.getValue();
				String confirmPassword = confirmPasswordField.getValue();
				String email = emailField.getValue();
				String confirmEmail = confirmEmailField.getValue();

				if(login.equals("")) {
					loginError.setText(Message.EMPTY_LOGIN);
					ok = false;
				}

				boolean passwordEmpty = false;
				if(password.equals("")) {
					passwordError.setText(Message.EMPTY_PASWORD);
					ok = false;
					passwordEmpty = true;
				}
				
				if(password.length() < 6) {
					passwordError.setText(Message.PASSWORD_TOO_SHORT);
					ok = false;
					passwordEmpty = true;
				}

				if(confirmPassword.equals("")) {
					confirmPasswordError.setText(Message.CONFIRM_PASSWORD);
					ok = false;
					passwordEmpty = true;
				}

				if(!passwordEmpty && !password.equals(confirmPassword)) {
					passwordError.setText(Message.PASSWORDS_DIFFERENT);
					ok = false;
				}

				boolean emailEmpty = false;
				if(email.equals("")) {
					emailError.setText(Message.EMPTY_EMAIL);
					ok = false;
					emailEmpty = true;
				}

				if(confirmEmail.equals("")) {
					confirmEmailError.setText(Message.CONFIRM_EMAIL);
					ok = false;
					emailEmpty = true;
				}

				if(!FieldVerifier.isValidEmailAddress(email)) {
					emailError.setText(Message.INVALID_EMAIL);
					ok = false;
				}

				if(!emailEmpty && !emailField.getValue().equals(confirmEmailField.getValue())) {
					emailError.setText(Message.EMAILS_DIFFERENT);
					ok = false;
				}

				if(ok && loginAvailable) {
					authService.createAccount(login, password, email, new AsyncCallback<Boolean>() {
						public void onFailure(Throwable arg0) {
							webApp.showError("Error", Message.SERVER_ERROR);
						}

						public void onSuccess(Boolean res) {
							if(res)
								webApp.registrationSuccess();
							else
								webApp.showError("Registration error", Message.ACCOUNT_CREATE_FAILURE);
						}
					});
				}
			}
		});

		addStyleName("loginForm");
		addStyleName("center");
	}

	class Pair extends HorizontalPanel {
		public Pair(Widget left, Widget right) {
			add(left);
			add(right);
		}
	}
	
	public static RegistrationForm getInstance(ContactBookWebapp webApp, ContactBookAuthServiceAsync authService) {
		if(instance == null)
			instance = new RegistrationForm(webApp, authService);
		
		return instance;
	}
}
