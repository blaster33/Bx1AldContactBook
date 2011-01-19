package contactbook.webapp.client.components.forms;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import contactbook.webapp.client.ContactBookWebapp;
import contactbook.webapp.client.business.ContactBookBusinessServiceAsync;
import contactbook.webapp.client.dto.ContactDTO;
import contactbook.webapp.client.dto.GroupDTO;
import contactbook.webapp.shared.Message;

public class ContactEditForm extends AsyncForm {
	public ContactEditForm(ContactBookWebapp webApp) {
		this(webApp, null);
	}
	
	public ContactEditForm(final ContactBookWebapp webApp, ContactDTO contact) {
		final Label lastNameLabel = new Label("");
		final Label firstNameLabel = new Label("");
		final Label homePhoneLabel = new Label("");
		final Label cellPhoneLabel = new Label("");
		final Label emailLabel = new Label("");
		final Label address1Label = new Label("");
		final Label address2Label = new Label("");
		final Label zipCodeLabel = new Label("");
		final Label cityLabel = new Label("");
		final Label stateLabel = new Label("");
		final Label countryLabel = new Label("");
		final Label dateOfBirthLabel = new Label("");
		
		final TextBox lastNameField = new TextBox();
		final TextBox firstNameField = new TextBox();
		final TextBox homePhoneField = new TextBox();
		final TextBox cellPhoneField = new TextBox();
		final TextBox emailField = new TextBox();
		final TextBox address1Field = new TextBox();
		final TextBox address2Field = new TextBox();
		final TextBox zipCodeField = new TextBox();
		final TextBox cityField = new TextBox();
		final TextBox stateField = new TextBox();
		final TextBox countryField = new TextBox();
		final TextBox dateOfBirthField = new TextBox();
		
		if(contact != null) {
			lastNameField.setText(contact.getLastName());
			firstNameField.setText(contact.getFirstName());
			homePhoneField.setText(contact.getHomePhone());
			cellPhoneField.setText(contact.getCellPhone());
			emailField.setText(contact.getEmail());
			address1Field.setText(contact.getAddress1());
			address2Field.setText(contact.getAddress2());
			zipCodeField.setText(contact.getZipCode());
			cityField.setText(contact.getCity());
			stateField.setText(contact.getState());
			countryField.setText(contact.getCountry());
			// TODO display proper date
			dateOfBirthField.setText("" + contact.getDateOfBirth());
		}
		
		add(new Label(Message.CONTACT_LAST_NAME));
		add(new WidgetPair(lastNameField, lastNameLabel));
		add(new Label(Message.CONTACT_FIRST_NAME));
		add(new WidgetPair(firstNameField, firstNameLabel));
		add(new Label(Message.CONTACT_HOME_PHONE));
		add(new WidgetPair(homePhoneField, homePhoneLabel));
		add(new Label(Message.CONTACT_CELL_PHONE));
		add(new WidgetPair(cellPhoneField, cellPhoneLabel));
		add(new Label(Message.CONTACT_EMAIL));
		add(new WidgetPair(emailField, emailLabel));
		add(new Label(Message.CONTACT_ADDRESS));
		add(new WidgetPair(address1Field, address1Label));
		add(new WidgetPair(address2Field, address2Label));
		add(new Label(Message.CONTACT_ZIP_CODE));
		add(new WidgetPair(zipCodeField, zipCodeLabel));
		add(new Label(Message.CONTACT_CITY));
		add(new WidgetPair(cityField, cityLabel));
		add(new Label(Message.CONTACT_STATE));
		add(new WidgetPair(stateField, stateLabel));
		add(new Label(Message.CONTACT_COUNTRY));
		add(new WidgetPair(countryField, countryLabel));
		add(new Label(Message.CONTACT_DOB));
		add(new WidgetPair(dateOfBirthField, dateOfBirthLabel));
		
		Button submitButton = new Button(Message.SAVE);
		add(submitButton);
		
		submitButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String firstName = firstNameField.getName();
				String lastName = lastNameField.getName();
				
				boolean ok = true;
				if(firstName.equals("")) {
					firstNameLabel.setText(Message.EMPTY_FIRST_NAME);
					ok = false;
				}
				
				if(lastName.equals("")) {
					lastNameLabel.setText(Message.EMPTY_LAST_NAME);
					ok = false;
				}
				
				if(!ok)
					return;
				
				ContactBookBusinessServiceAsync contactService = webApp.getBusinessService();
				ContactDTO dto = new ContactDTO();
				
				dto.setLastName(lastNameField.getText());
				dto.setFirstName(firstNameField.getText());
				dto.setHomePhone(homePhoneField.getText());
				dto.setCellPhone(cellPhoneField.getText());
				dto.setEmail(emailField.getText());
				dto.setAddress1(address1Field.getText());
				dto.setAddress2(address2Field.getText());
				dto.setZipCode(zipCodeField.getText());
				dto.setCity(cityField.getText());
				dto.setState(stateField.getText());
				dto.setCountry(countryField.getText());
				// TODO display proper date
				dto.setDateOfBirth(Long.parseLong(dateOfBirthField.getText()));
				
				
				contactService.addGroup(dto, new AsyncCallback<Boolean>() {
					public void onSuccess(Boolean res) {
						webApp.refreshLeft();
						observable.formValidated();
					}
					
					public void onFailure(Throwable arg0) {
						webApp.showError(Message.ERROR, Message.ERROR_SAVING_GROUP);
						observable.formValidated();
					}
				});
			}
		});
	}
}
