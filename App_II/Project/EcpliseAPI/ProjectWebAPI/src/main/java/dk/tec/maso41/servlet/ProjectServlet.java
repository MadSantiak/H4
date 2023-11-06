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
import dk.tec.maso41.Haircolor;
import dk.tec.maso41.Person;
import java.util.logging.Logger;

public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * Allows the API to call get-methods.
		 * Using AnalyzeRequest to parse whether the request is asking for "All Persons"
		 * or a specific recordset.
		 * Using ObjectMapper to write the data in the response to be read by the
		 * App's ApiLayer.
		 */
		PrintWriter out = response.getWriter();
		AnalyzeRequest analyze = new AnalyzeRequest(request.getPathInfo());
		ObjectMapper mapper = new ObjectMapper();
		DBTools db = new DBTools();
		
		switch (analyze.getMatch()) {
			case MatchPersonId:
				Person p = db.getPersonById(analyze.getId());
				String json = mapper.writeValueAsString(p);
				out.print(json);
				break;
			case MatchPerson:
				List<Person> people = db.getAllPerson();
				String jsonAll = mapper.writeValueAsString(people);
				out.print(jsonAll);
				break;
			case MatchHaircolor:
				List<Haircolor> haircolors = db.getAllHaircolor();
				String jsonAllHair = mapper.writeValueAsString(haircolors);
				out.print(jsonAllHair);;
				break;
			case MatchHaircolorId:
				Haircolor hc = db.getHaircolorById(analyze.getId());
				String jsonHC = mapper.writeValueAsString(hc);
				out.print(jsonHC);
				break;
			case MatchNo:
				out.write("\nNo such person..");
				break;
		default:
			break;
			}
		}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/**
		 * Allows the App to call the API and essentially create a Person object
		 * via the DBTools addPerson() function.
		 */
		PrintWriter out = resp.getWriter();
		DBTools db = new DBTools();
		BufferedReader reader = req.getReader();
		String recJSON = reader.readLine();
		System.out.println(recJSON);
		ObjectMapper mapper = new ObjectMapper();
		Person p = mapper.readValue(recJSON, Person.class);
		String resInt = mapper.writeValueAsString(db.addPerson(p));
		System.out.println(resInt);
		out.print(resInt);
		
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/**
		 * Allows the App to call the API and then, via the DBtools class
		 * instruct the database to remove the row with the ID fetched from the 
		 * Person object in question, passed along in the Path of the request,
		 * which is finally extracted using the AnalyseRequest class.
		 */
		AnalyzeRequest analyze = new AnalyzeRequest(req.getPathInfo());
		ObjectMapper mapper = new ObjectMapper();
		DBTools db = new DBTools();
		db.delPerson(analyze.getId());
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AnalyzeRequest analyze = new AnalyzeRequest(req.getPathInfo());
		ObjectMapper mapper = new ObjectMapper();
		DBTools db = new DBTools();
		BufferedReader reader = req.getReader();
		String updJSON = reader.readLine();
		Person p = mapper.readValue(updJSON, Person.class);
		db.updatePerson(p);
	}
	
}
