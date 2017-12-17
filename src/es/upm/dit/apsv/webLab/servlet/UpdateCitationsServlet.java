package es.upm.dit.apsv.webLab.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import es.upm.dit.apsv.webLab.dao.PublicationDAO;
import es.upm.dit.apsv.webLab.dao.PublicationDAOImpl;
import es.upm.dit.apsv.webLab.model.Publication;


@WebServlet("/UpdateCitationsServlet")
public class UpdateCitationsServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PublicationDAO daop = PublicationDAOImpl.getInstance();
		String pid = req.getParameter("pubid");

		String APIKey = "2382c3ba18539b207cdc8f64a57e6909";
		String urlAPI = "https://api.elsevier.com/content/abstract/scopus_id/" + pid + "?apiKey=" + APIKey;
		JSONObject result = getAPI(urlAPI);

		if (result != null) {
			JSONObject arr = (JSONObject) result.get("abstracts-retrieval-response");
			JSONObject coredata = (JSONObject) arr.get("coredata");
			int citeCount = Integer.parseInt((String) coredata.get("citedby-count"));

			Publication pub = daop.read(pid);
			pub.setCiteCount(citeCount);
			daop.update(pub);

		}
		resp.sendRedirect("/APSV/ViewResearcher.jsp");
	}

	private JSONObject getAPI(String targetUrl) {
		JSONObject object = null;
		try {
			URL url = new URL(targetUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			int responseCode = connection.getResponseCode();
			if(responseCode>=200 && responseCode<300) {
				InputStream is = connection.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				object =(JSONObject) new JSONParser().parse(isr);
				is.close();
			} else {
				System.err.println("Petition returned code "+ responseCode);
				System.err.println(connection.getResponseMessage());
			}
			connection.getResponseCode();
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
}
