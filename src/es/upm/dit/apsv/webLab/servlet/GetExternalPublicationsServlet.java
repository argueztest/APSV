package es.upm.dit.apsv.webLab.servlet;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.activemq.command.ActiveMQQueue;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import es.upm.dit.apsv.webLab.dao.PublicationDAO;
import es.upm.dit.apsv.webLab.dao.PublicationDAOImpl;
import es.upm.dit.apsv.webLab.dao.ResearcherDAO;
import es.upm.dit.apsv.webLab.dao.ResearcherDAOImpl;
import es.upm.dit.apsv.webLab.model.Publication;
import es.upm.dit.apsv.webLab.model.Researcher;

@WebServlet("/GetExternalPublicationsServlet")
public class GetExternalPublicationsServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			Researcher r = (Researcher) req.getSession().getAttribute("researcher");
			PublicationDAO daop = PublicationDAOImpl.getInstance();
			ResearcherDAO daor = ResearcherDAOImpl.getInstance();
			ConnectionFactory connectionFactory =
					new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
			Destination destination = new ActiveMQQueue(r.getId());
			Connection connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageConsumer consumer = session.createConsumer(destination);

			while(true) {
				ActiveMQBytesMessage message = (ActiveMQBytesMessage) consumer.receive(100);
				if (message == null) {
					break;
				}
				String content = new String(message.getContent().getData());
				JSONObject obj = (JSONObject) new JSONParser().parse(content);
				Publication p = new Publication(
						(String)obj.get("id"),
						(String)obj.get("title"),
						0);

				String[] authors = ((String) obj.get("authors")).split(",");
				for(String auth : authors) {
					Researcher res = daor.read(auth);
					if(null != res) p.getAuthors().add(res);
				}
				daop.update(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect("/APSV/ViewResearcher.jsp");

	}
}
