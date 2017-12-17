package es.upm.dit.apsv.webLab.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.apsv.webLab.dao.ResearcherDAOImpl;
import es.upm.dit.apsv.webLab.model.Researcher;

@WebServlet("/CreateResearcherServlet")
public class CreateResearcherServlet extends HttpServlet{
	/**
	 * Constant parameters
	 */
	private static final long serialVersionUID = 1L;
	private static final String admin = "admin";
	
    /**
     * Default constructor. 
     */
    public CreateResearcherServlet() {
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) 
					throws ServletException, IOException {
		
    	String researcher_id = req.getParameter("researcher_id");
    	String researcher_name = req.getParameter("researcher_name");
		String researcher_aff = req.getParameter("researcher_aff");
		String researcher_email = req.getParameter("researcher_email");
		String researcher_pwd = req.getParameter("researcher_pwd");
		
		//	Session user obtaining
		Researcher session_user = (Researcher) req.getSession().getAttribute("user"); 
        System.out.println("Researcher name: " + session_user.getName());

		if (admin.equals(session_user.getName())) {
			ResearcherDAOImpl dao = ResearcherDAOImpl.getInstance();
			dao.create(new Researcher(researcher_id, researcher_name, researcher_email, researcher_pwd, researcher_aff));
			resp.sendRedirect("/APSV/RootView.jsp");
		} else {
			resp.sendRedirect(req.getContextPath() + "/index.html");
		}
	}

}

