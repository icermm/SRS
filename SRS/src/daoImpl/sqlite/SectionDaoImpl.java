package daoImpl.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.SectionDao;
import model.Course;
import model.Professor;
import model.Section;
import model.Student;
import util.DbUtil;


public class SectionDaoImpl implements SectionDao {

	@Override
	public List<Section> getSections() {
		
		List<Section> Sections = new ArrayList<Section>();
		Section s;Course c;Professor p;
		String findAllSections="select * from Section";
		Connection Conn = DbUtil.getSqliteConnection();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(findAllSections);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int sectionNo = rs.getInt("sectionNo");
				String dayOfWeek = rs.getString("dayOfWeek");
				String timeOfDay = rs.getString("timeOfDay");
				String room = rs.getString("room");
				int seatingCapacity = rs.getInt("seatingCapacity");
				String courseNo = rs.getString("courseNo");
				String PSsn = rs.getString("PSsn");
				c=new Course(courseNo, "", 0);
				s=new Section(sectionNo, dayOfWeek, timeOfDay,c,room,seatingCapacity);
				p=new Professor(PSsn,"","","","");
				s.setInstructor(p);
				Sections.add(s);			
			}
			rs.close();
			pstmt.close();
			Conn.close();
		}catch(Exception e){
		    e.printStackTrace();	
		}
		return Sections;
	}

	@Override
	public List<Student> getEnrolledStudents(int sectionNo) {
		
		List<Student> enrolledStudents = new ArrayList<Student>();
		Student student;
		String getEnrolledStudents = "select * from Transcript where sectionNo="+sectionNo;
		Connection Conn = DbUtil.getSqliteConnection();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(getEnrolledStudents);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				student = new Student(rs.getString("SSsn"),"","","","");
				enrolledStudents.add(student);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return enrolledStudents;
	}


	@Override
	public List<Section> getCourseSection(String courseNo) {
		
		List<Section> CourseSection = new ArrayList<Section>();
		Section s = null;Course c;Professor p;
		Connection Conn = DbUtil.getSqliteConnection();
		String searchCourseSection = "select * from Section where courseNo='"+courseNo+"'";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(searchCourseSection);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int sectionNo = rs.getInt("sectionNo");
				String dayOfWeek = rs.getString("dayOfWeek");
				String timeOfDay = rs.getString("timeOfDay");
				String room = rs.getString("room");
				int seatingCapacity = rs.getInt("seatingCapacity");
				String PSsn = rs.getString("PSsn");
				c=new Course(courseNo, "", 0.0);
				p=new Professor(PSsn,"","","","");
				s=new Section(sectionNo, dayOfWeek, timeOfDay,c,room,seatingCapacity);
				s.setInstructor(p);
				CourseSection.add(s);	
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return CourseSection;
	}

	@Override
	public void addSection(Section section) {
		
		Connection Conn = DbUtil.getSqliteConnection();
		String addSection = "insert into Section (sectionNo,dayOfWeek,timeOfDay,courseNo,room,seatingCapacity) values(?,?,?,?,?,?)";
		try {		
			PreparedStatement pstmt = Conn.prepareStatement(addSection);
			pstmt.setInt(1, section.getSectionNo());
			pstmt.setString(2, section.getDayOfWeek());
			pstmt.setString(3, section.getTimeOfDay());
			pstmt.setString(4, section.getRepresentedCourse().getCourseNo());
			pstmt.setString(5, section.getRoom());
			pstmt.setInt(6, section.getSeatingCapacity());
			pstmt.executeUpdate();
			pstmt.close();
			Conn.close();
			System.out.println("Ìí¼ÓSection³É¹¦");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	
	@Override
	public Section searchSection(int sectionNo) {
		
		Section s = null;Course c;Professor p;
		Connection Conn = DbUtil.getSqliteConnection();
		String searchSection = "select * from Section where sectionNo="+sectionNo;
		try {
			PreparedStatement pstmt = Conn.prepareStatement(searchSection);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String dayOfWeek = rs.getString("dayOfWeek");
				String timeOfDay = rs.getString("timeOfDay");
				String room = rs.getString("room");
				int seatingCapacity = rs.getInt("seatingCapacity");
				String courseNo = rs.getString("courseNo");
				String PSsn = rs.getString("PSsn");
				c=new Course(courseNo, "", 0.0);
				s=new Section(sectionNo, dayOfWeek, timeOfDay,c,room,seatingCapacity);
				p=new Professor(PSsn,"","","","");
				s.setInstructor(p);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	
}
