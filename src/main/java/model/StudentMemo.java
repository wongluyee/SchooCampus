package model;

/**
 * 学生メモ情報クラス
 */
public class StudentMemo {

	private String studentNumber; // 学生番号
	private String studentName; // 学生名
	private String staffId; // スタッフID
	private String staffName; // スタッフ名
	private String memoId; // メモID
	private String memo; // メモ内容

	/**
	 * コンストラクタ
	 * @param studentNumber
	 * @param studentName
	 * @param staffId
	 * @param staffName
	 * @param memoId
	 * @param memo
	 */
	public StudentMemo(
			String studentNumber, String studentName,
			String staffId, String staffName,
			String memoId, String memo) {
		this.studentNumber = studentNumber;
		this.studentName = studentName;
		this.staffId = staffId;
		this.staffName = staffName;
		this.memoId = memoId;
		this.memo = memo;
	}
	/**
	 * コンストラクタ<br>
	 * 新規登録の場合を想定しスタッフの情報のみ保持
	 * @param staffId
	 * @param staffName
	 */
	public StudentMemo(String staffId, String staffName) {
		this.studentNumber = "";
		this.studentName = "";
		this.staffId = staffId;
		this.staffName = staffName;
		this.memoId = "";
		this.memo = "";
	}

	/**
	 * 学生番号を返します
	 * @return studentNumber
	 */
	public String getStudentNumber() {
		return studentNumber;
	}
	/**
	 * 学生番号をセットします
	 * @param studentNumber セットする studentNumber
	 */
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	/**
	 * 学生名を返します
	 * @return studentName
	 */
	public String getStudentName() {
		return studentName;
	}
	/**
	 * 学生名をセットします
	 * @param studentName セットする studentName
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	/**
	 * スタッフIDを返します
	 * @return staffId スタッフID
	 */
	public String getStaffId() {
		return staffId;
	}
	/**
	 * スタッフIDをセットします
	 * @param staffId セットする staffId
	 */
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	/**
	 * スタッフ名を返します
	 * @return staffName スタッフ名
	 */
	public String getStaffName() {
		return staffName;
	}
	/**
	 * スタッフ名をセットします
	 * @param staffName セットする staffName
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	/**
	 * メモIDを返します
	 * @return memoId メモID
	 */
	public String getMemoId() {
		return memoId;
	}
	/**
	 * メモIDをセットします
	 * @param memoId セットする memoId
	 */
	public void setMemoId(String memoId) {
		this.memoId = memoId;
	}
	/**
	 * メモを返します
	 * @return memo
	 */
	public String getMemo() {
		return memo;
	}
	/**
	 * メモIDをセットします
	 * @param memo セットする memo
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
}