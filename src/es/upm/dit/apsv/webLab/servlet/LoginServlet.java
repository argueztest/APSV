package es.upm.dit.apsv.webLab.servlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.apsv.webLab.dao.ResearcherDAO;
import es.upm.dit.apsv.webLab.dao.ResearcherDAOImpl;
import es.upm.dit.apsv.webLab.model.Researcher;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	
	/**
	 * Constant parameters
	 */
	private static final long serialVersionUID = 1L;
	private static final String admin = "admin";
	private static final String adminPassword = "admin";
	

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) 
					throws ServletException, IOException {
		
		String email = req.getParameter("email");
	    String pwd = req.getParameter("pwd");
        System.out.println("Researcher requested email:" + email + " pass:" + pwd);

	    ResearcherDAOImpl dao = ResearcherDAOImpl.getInstance();
	    Researcher r = dao.searchUser(email, pwd);
        System.out.println("Researcher found email:" + r.getEmail() + " pass:" + r.getPassword());

	    if (admin.equals(email) && adminPassword.equals(pwd)) {
	        System.out.println("RootView");
	               // dummy Researcher for the root user 
	        req.getSession().setAttribute("user", new Researcher("0", admin, admin, "", ""));
	        resp.sendRedirect("/APSV/RootView.jsp");
	    } else if (r != null && email.equals(r.getEmail()) && pwd.equals(r.getPassword())){
	        System.out.println("ViewProfile");
	        req.getSession().setAttribute("user", r);
	        req.getSession().setAttribute("researcher", r);
	        resp.sendRedirect("/APSV/ViewProfile.jsp");
	    } else{
	        System.out.println("Index");
	        resp.sendRedirect(req.getContextPath() + "/index.html");
	    }

	}
}

