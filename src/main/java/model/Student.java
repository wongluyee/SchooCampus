package model;

/**
 * 学生情報クラス
 */
public class Student {

	private String studentNumber; // 学生番号
	private String studentName; // 学生名

	// ★ここにコードを書く★
	// Constructor
	public Student(String studentNumber, String studentName) {
		this.studentNumber = studentNumber;
		this.studentName = studentName;
	}

	// Accessor
	// Getter
	public String getStudentNumber() {
		return this.studentNumber;
	}

	public String getStudentName() {
		return this.studentName;
	}
	
	// Setter
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public void setStudentName(String studentName) {
		this.studentName= studentName;
	}
}