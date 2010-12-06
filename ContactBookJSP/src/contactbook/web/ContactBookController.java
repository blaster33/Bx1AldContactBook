package contactbook.web;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContactBookController extends HttpServlet implements Servlet {
	private static final long serialVersionUID = -8747927932533229991L;
	
	public ContactBookController() {
		super();
	}
	public void init() throws ServletException {
		// TODO
		super.init();
	}
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
	throws ServletException, IOException {
		// TODO
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
	throws ServletException, IOException {
		// TODO
	}
	public void destroy() {
		super.destroy();
	}
}
