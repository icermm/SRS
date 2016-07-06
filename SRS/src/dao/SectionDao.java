package dao;

import java.util.List;

import model.Section;
import model.Student;

public interface SectionDao {
	List<Section> getSections();
	List<Student> getEnrolledStudents(int sectionNo);
	List<Section> getCourseSection(String courseNo);
	void addSection(Section section);
	Section searchSection(int sectionNo);
}

