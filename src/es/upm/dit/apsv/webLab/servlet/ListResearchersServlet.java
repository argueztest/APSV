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

@WebServlet ("/ListResearchersServlet")
public class ListResearchersServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ResearcherDAO dao= ResearcherDAOImpl.getInstance();
		Collection<Researcher> researchers = dao.readAll();
		req.getSession().setAttribute("rs", researchers);
		resp.sendRedirect("/APSV/ListResearchers.jsp"); //Redirect petition to JSP
	}
}
