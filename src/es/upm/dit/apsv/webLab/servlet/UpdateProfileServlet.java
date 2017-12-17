package es.upm.dit.apsv.webLab.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.apsv.webLab.dao.ResearcherDAOImpl;
import es.upm.dit.apsv.webLab.model.Researcher;

@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet{
	/**
	 * Constant parameters
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     * Default constructor. 
     */
    public UpdateProfileServlet() {
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) 
					throws ServletException, IOException {
		
    	String researcher_name = req.getParameter("researcher_name");
		String researcher_aff = req.getParameter("researcher_aff");
		String researcher_email = req.getParameter("researcher_email");
		String researcher_pwd = req.getParameter("researcher_pwd");
		
		//	Session user obtaining
		Researcher session_user = (Researcher) req.getSession().getAttribute("user");
		
		if (session_user != null) {
			ResearcherDAOImpl dao = ResearcherDAOImpl.getInstance();
			Researcher updated_r = new Researcher(session_user.getId(), researcher_name, researcher_email, researcher_pwd, researcher_aff);
			dao.update(updated_r);
	        req.getSession().setAttribute("user", updated_r);
	        req.getSession().setAttribute("researcher", updated_r);
			resp.sendRedirect("/APSV/ViewProfile.jsp");
		} else {
			resp.sendRedirect(req.getContextPath() + "/index.html");
		}
	}
}
