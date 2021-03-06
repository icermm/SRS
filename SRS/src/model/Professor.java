package model;

import java.util.ArrayList;

public class Professor extends Person {
	
	private String title;
	private String department;
	private ArrayList<Section> teaches; 

	public Professor(String ssn, String name, String password, String title, String dept) {
		super(ssn, name, password);
		setTitle(title);
		setDepartment(dept);
		teaches = new ArrayList<Section>();
	}
		
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}

	public void setDepartment(String dept) {
		this.department = dept;
	}

	public String getDepartment() {
		return department;
	}
	
	public void agreeToTeach(Section s) {
		teaches.add(s);
		s.setInstructor(this);
	}
	public void display() {
		

		System.out.println("Professor-Specific Information:");
		System.out.println("\tTitle:  " + getTitle());
		System.out.println("\tTeaches for Dept.:  " + getDepartment());

		System.out.println();
	}

}
