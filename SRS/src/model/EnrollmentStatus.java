package model;



public enum EnrollmentStatus {
	success("ѡ�γɹ���"), 
	secFull("ѡ��ʧ�ܣ��������㣡"), 
	prereq("ѡ��ʧ�ܣ�δѧϰ���޿γ̣�"), 
	prevEnroll("ѡ��ʧ�ܣ����޹��γ̣�");
	private final String value;

	
	EnrollmentStatus(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}
