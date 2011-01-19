package contactbook.webapp.client.components.forms;

import java.util.Observable;
import java.util.Observer;

import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class AsyncForm extends VerticalPanel {
	protected PrivateObservable observable = new PrivateObservable();
	
	public void setValidated() {
		observable.formValidated();
	}
	
	/*
	 * Delegate methods
	 */
	public void addObserver(Observer o) {
		observable.addObserver(o);
	}

	public int countObservers() {
		return observable.countObservers();
	}

	public void deleteObserver(Observer o) {
		observable.deleteObserver(o);
	}

	public void deleteObservers() {
		observable.deleteObservers();
	}

	public void formValidated() {
		observable.formValidated();
	}

	public boolean hasChanged() {
		return observable.hasChanged();
	}

	public void notifyObservers() {
		observable.notifyObservers();
	}

	public void notifyObservers(Object arg) {
		observable.notifyObservers(arg);
	}
	
	
	class PrivateObservable extends Observable {
		public void formValidated() {
			setChanged();
		}
	}
}
