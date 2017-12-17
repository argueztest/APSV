/*package es.upm.dit.apsv.webLab.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class UpdateCitationsServlet extends HttpServlet{
	*//**
	 * Constant parameters
	 *//*
	private final String apikey= "numeraco";
	private static final long serialVersionUID = 1L;
	
    *//**
     * Default constructor. 
     *//*
    public UpdateCitationsServlet() {
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) 
					throws ServletException, IOException {
    	
    	String researcherid = req.getParameter("rsi");
    	String publicationid = req.getParameter("publicationId");
    	
    	try {
    		String url = "https://api.elsevier.com/content/abstract/scopus_id/"+ publicationid+"?apiKey="+apikey;
    		JSONObject result = this.getAPI(url);
    		if (result != null) {
    			JSONObject arr = (JSONObject) result.get("abstracts-retrieval-response");
    			JSONObject coredata = (JSONObject) arr.get("coredata");
    			int citeCount = Integer.parseInt((String)   coredata.get("citedby-count"));
    			PublicationDAO pdao = PublicationDAOImpl.getInstance();
    			Publication pub = pdao.read(new Publication(publicationid, "", 0));
    			pub.setCiteCount(citeCount);
    			
    	}}catch(Exception e) {
    		
    	}
    	resp.sendRedirect("ViewResearcher.jsp?rsi="+researcherid);
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
        	}
        	connection.getResponseCode();
        	connection.disconnect();
  	} catch (Exception e) {
     	}
  	return object;
}

}
*/