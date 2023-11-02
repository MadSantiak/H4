package dk.tec.maso41.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dk.tec.maso41.AnalyzeRequest;
import dk.tec.maso41.Person;

public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		// Alternative to the above:
		PrintWriter out = response.getWriter();
		out.print("\n Context Path:" + request.getContextPath());
		out.write("\n Path Info:" + request.getPathInfo());
		out.write("\n Servlet Path:" + request.getServletPath());
		out.write("\n....");
		AnalyzeRequest analyze = new AnalyzeRequest(request.getPathInfo());
		out.write("\n....analyzer");
		ObjectMapper mapper = new ObjectMapper();
		out.write("\n....mapper");
		DBTools db = new DBTools();
		out.write("\n....db");
		out.write("\n Getting Person(s)");
		
	
		switch (analyze.getMatch()) {
			case MatchPersonId:
				Person p = db.getPersonById(analyze.getId());
				out.write("\n" + mapper.writeValueAsString(p));
				break;
			case MatchPerson:
				break;
			case MatchNo:
				out.write("\nNo such person..");
				break;
			}
		}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String recJSON = req.getReader().readLine();
		ObjectMapper mapper = new ObjectMapper();
		Person p = mapper.readValue(recJSON, Person.class);
		System.out.println(p.getName());
		
	}
}
