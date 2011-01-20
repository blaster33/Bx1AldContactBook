package contactbook.webapp.client.components.forms;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.user.client.ui.VerticalPanel;

import contactbook.webapp.client.components.util.FormObserver;

public abstract class AsyncForm extends VerticalPanel {
	private Set<FormObserver> observers = new HashSet<FormObserver>();
	private boolean changed = false;
	
	public void formValidated() {
		setChanged();
		notifyObservers();
	}
	
	protected void setChanged() {
		changed = true;
	}
	
	public void addObserver(FormObserver o) {
		observers.add(o);
	}

	public int countObservers() {
		return observers.size();
	}

	public void deleteObserver(FormObserver o) {
		observers.remove(o);
	}

	public void deleteObservers() {
		observers.clear();
	}

	public boolean hasChanged() {
		return changed;
	}

	public void notifyObservers() {
		for(FormObserver o: observers)
			o.update(this);
		changed = false;
	}
}
