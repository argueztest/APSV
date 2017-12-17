package es.upm.dit.apsv.webLab.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.apsv.webLab.dao.ResearcherDAOImpl;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet{
	/**
	 * Constant parameters
	 */
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LogoutServlet() {
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) 
					throws ServletException, IOException {
    	
    	req.getSession().removeAttribute("user");
    	req.getSession().invalidate();
    	resp.sendRedirect("/APSV");
	}
}

