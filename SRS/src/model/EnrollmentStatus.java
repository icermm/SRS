package model;



public enum EnrollmentStatus {
	success("选课成功！"), 
	secFull("选课失败，容量不足！"), 
	prereq("选课失败，未学习先修课程！"), 
	prevEnroll("选课失败，已修过课程！");
	private final String value;

	
	EnrollmentStatus(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}
