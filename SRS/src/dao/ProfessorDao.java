package dao;

import java.util.List;

import model.Professor;
import model.Section;

public interface ProfessorDao {
	List<Professor> getProfessors();
	List<Section> getteached(String Pssn);	
	void addProfessor(Professor professor);
	void deleteProfessor(String Pssn);
	void updateProfessor(Professor professor);
	Professor searchProfessor(String Pssn);
	String searchPSsn(String name);
	String getPassword(String SSsn);
	void teachSection(Professor professor, Section section);
}
