package es.upm.dit.apsv.webLab.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.apsv.webLab.dao.ResearcherDAO;
import es.upm.dit.apsv.webLab.dao.ResearcherDAOImpl;
import es.upm.dit.apsv.webLab.model.Researcher;

@WebServlet("/ViewResearcherServlet")
public class ViewResearcherServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String researcherId= (String) req.getParameter("rsi");
		ResearcherDAO dao = ResearcherDAOImpl.getInstance();
		Researcher researcher = dao.read(new Researcher(researcherId, null, null, null,null));
		req.getSession().setAttribute("researcher", researcher);
		resp.sendRedirect("/APSV/ViewResearcher.jsp");
	}

}