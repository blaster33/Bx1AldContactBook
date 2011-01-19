package contactbook.webapp.client.components.forms;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class WidgetPair extends HorizontalPanel {
	public WidgetPair(Widget left, Widget right) {
		add(left);
		add(right);
	}
}
