package contactbook.webapp.server;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import contactbook.webapp.client.GreetingService;
import contactbook.webapp.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Query string: " + req.getQueryString());			
		
		Enumeration params = req.getParameterNames();
		while(params.hasMoreElements()) {
			String param = (String) params.nextElement();
			String value = req.getParameter(param);
			System.out.println(param + " -> " + value);
		}
		
		greetServer("username");
		super.doGet(req, resp);
	}

	public String greetServer(String input) throws IllegalArgumentException {
		this.getServletConfig();
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br>" + input + ": it's not my time<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}
