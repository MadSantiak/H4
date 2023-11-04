package dk.tec.maso41.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dk.tec.maso41.AnalyzeRequest;
import dk.tec.maso41.Person;
import java.util.logging.Logger;
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Alternative to the above:
		PrintWriter out = response.getWriter();
		AnalyzeRequest analyze = new AnalyzeRequest(request.getPathInfo());
		ObjectMapper mapper = new ObjectMapper();
		DBTools db = new DBTools();
		System.out.println("GET REQUEST");
		System.out.println(request);
		
		switch (analyze.getMatch()) {
			case MatchPersonId:
				Person p = db.getPersonById(analyze.getId());
				String json = mapper.writeValueAsString(p);
				System.out.println("BY ID: "+ json);
				out.print(json);
				break;
			case MatchPerson:
				List<Person> people = db.getAllPerson();
				String jsonAll = mapper.writeValueAsString(people);
				System.out.println(jsonAll);
				out.print(jsonAll);
				break;
			case MatchNo:
				out.write("\nNo such person..");
				break;
			}
		}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		BufferedReader reader = req.getReader();
		String recJSON = reader.readLine();
		System.out.println(recJSON);
		ObjectMapper mapper = new ObjectMapper();
		Person p = mapper.readValue(recJSON, Person.class);
		System.out.println(p.getName());
		
	}
}
