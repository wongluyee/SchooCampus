package model;

/**
 * スタッフ情報クラス
 */
public class Staff {
	private String staffId; // スタッフID
	private String staffName; // スタッフ名
	private String loginPassword; // ログインパスワード

	// ★ここにコードを書く★
	// Constructor
	public Staff(String staffId, String staffName, String loginPassword) {
		this.staffId = staffId;
		this.staffName = staffName;
		this.loginPassword = loginPassword;
	}
	
	// Accessor
	// Getter
	public String getStaffId() {
		return this.staffId;
	}
	public String getStaffName() {
		return this.staffName;
	}
	public String getLoginPassword() {
		return this.loginPassword;
	}
	
	// Setter
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
}