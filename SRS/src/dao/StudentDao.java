package dao;

import java.util.List;

import model.Section;
import model.Student;

public interface StudentDao {
	List<Student> getStudents();
	String getPassword(String SSsn);
	List<Section> getEnrolledSections(Student student);
	void addStudent(Student student);
	void deleteStudent(Student student);
	void updateStudent(Student student);
	Student searchStudent(String SSsn);
}
