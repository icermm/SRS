package service;

import java.util.HashMap;
import java.util.List;

import dao.CourseDao;
import dao.DataAccess;
import dao.ProfessorDao;
import model.Course;
import model.Faculty;
import model.Professor;
import model.Section;
import util.json.JSONArray;
import util.json.JSONObject;

public class professorService {
	private static Faculty  Professors;
	
	ProfessorDao pd = DataAccess.createProfessorDao();
	HashMap<String,Professor> professors = new HashMap<String, Professor>();
	List<Professor> professor = pd.getProfessors();
	
	public professorService(){
		for(Professor p:professor){
			professors.put(p.getSsn(),p);
		}
		
		Professors=new Faculty(professors);
	}
	
	public Faculty getFaculty() {
		return Professors;
	}
	
	public String getProfessor(String PSsn) {
		JSONObject jo = new JSONObject();
		Professor p = pd.searchProfessor(PSsn);
		jo.put("PSsn", p.getSsn());	
		jo.put("name", p.getName());
		jo.put("title", p.getTitle());
		jo.put("department", p.getDepartment());
		jo.put("password", p.getPassword());
		return jo.toString();
	}
	public String getProfessors() {
		JSONArray ja = new JSONArray();
		for(Professor p:professor){
			JSONObject jo = new JSONObject();
			jo.put("PSsn", p.getSsn());
			jo.put("title", p.getTitle());
			jo.put("name", p.getName());
			jo.put("department", p.getDepartment());
			ja.put(jo);
		}
		return ja.toString();
	}
	
}
