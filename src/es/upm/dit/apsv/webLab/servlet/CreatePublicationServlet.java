package es.upm.dit.apsv.webLab.servlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.apsv.webLab.dao.PublicationDAO;
import es.upm.dit.apsv.webLab.dao.PublicationDAOImpl;
import es.upm.dit.apsv.webLab.dao.ResearcherDAO;
import es.upm.dit.apsv.webLab.dao.ResearcherDAOImpl;
import es.upm.dit.apsv.webLab.model.Publication;
import es.upm.dit.apsv.webLab.model.Researcher;


@WebServlet("/CreatePublicationServlet")
public class CreatePublicationServlet extends HttpServlet{
	/**
	 * Constant parameters
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     * Default constructor. 
     */
    public CreatePublicationServlet() {
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) 
					throws ServletException, IOException {
		
    	String pub_id = req.getParameter("pub_id");
		String pub_title = req.getParameter("pub_title");
		int pub_cit = Integer.parseInt(req.getParameter("pub_cit"));
		
		//	Session user obtaining
		Researcher session_user = (Researcher) req.getSession().getAttribute("user");
		
		ResearcherDAO rDao = ResearcherDAOImpl.getInstance();
		PublicationDAO pDao = PublicationDAOImpl.getInstance();
		
		//	Researcher and Publication creation
		Researcher r = rDao.read(session_user);
		Publication pub = new Publication(pub_id, pub_title, pub_cit);
		
		//	Authors to Publication addition
		Collection<Researcher> authors = pub.getAuthors();
		authors.add(r);
		pub.setAuthors(authors);
		pDao.create(pub);
		
		//	Publication to Author addition
		Collection<Publication> pubs = r.getPublications();
		pubs.add(pub);
		r.setPublications(pubs);
		rDao.update(r);
		
		resp.sendRedirect("/APSV/ViewProfile.jsp");
	}
}
