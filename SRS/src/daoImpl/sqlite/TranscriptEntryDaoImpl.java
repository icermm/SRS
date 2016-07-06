package daoImpl.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.TranscriptEntryDao;
import model.Course;
import model.Section;
import model.Student;
import model.TranscriptEntry;
import util.DbUtil;

public class TranscriptEntryDaoImpl implements TranscriptEntryDao{
	
	@Override
	public void addTranscriptEntry(TranscriptEntry transcriptEntry) {
		
		Connection Conn = DbUtil.getSqliteConnection();
		String addTranscriptEntry = "insert into Transcript (SSsn,sectionNo,grade) values(?,?,?)";
		try {		
			PreparedStatement pstmt = Conn.prepareStatement(addTranscriptEntry);
			pstmt.setString(1, transcriptEntry.getStudent().getSsn());
			pstmt.setInt(2, transcriptEntry.getSection().getSectionNo());	
			pstmt.setString(3, "");
			pstmt.executeUpdate();
			pstmt.close();
			Conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteTranscriptEntry(TranscriptEntry transcriptEntry) {
		
		Connection Conn = DbUtil.getSqliteConnection();
		String deleteTranscriptEntry = "delete from Transcript where SSsn='"+transcriptEntry.getStudent().getSsn()+"'and sectionNo='"+transcriptEntry.getSection().getSectionNo()+"'";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(deleteTranscriptEntry);
			pstmt.executeUpdate();
			pstmt.close();
			Conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateGrade(TranscriptEntry transcriptEntry) {
		
		Connection Conn = DbUtil.getSqliteConnection();
		String updateGrade = "update Transcript set grade=? "
				+ "where SSsn='"+transcriptEntry.getStudent().getSsn()+"'and sectionNo='"+transcriptEntry.getSection().getSectionNo()+"'";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(updateGrade);
			pstmt.setString(1, transcriptEntry.getGrade());
			pstmt.executeUpdate();
			pstmt.close();
			Conn.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public TranscriptEntry searchTranscriptEntry(String SSsn, int sectionNo) {
		TranscriptEntry t = null;
		Course co = null;Student stu;Section s;
		Connection Conn = DbUtil.getSqliteConnection();
		String searcuTranscriptEntry = "select * from Transcript "
				+ "where SSsn='"+SSsn+"'and sectionNo='"+sectionNo+"'";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(searcuTranscriptEntry);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {	
				String grade = rs.getString("grade");
				stu=new Student(SSsn,"","","","");
				s=new Section(sectionNo, "", "",co,"",0);
				t=new TranscriptEntry(stu,grade,s);				
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

}
