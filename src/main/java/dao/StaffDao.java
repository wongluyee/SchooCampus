package dao;

import java.sql.SQLException;

import exception.CampusException;
import model.Staff;

/**
 * スタッフ情報関連のDAOクラス
 */
public class StaffDao extends BaseDao {
	/**
	 * コンストラクタ<br>
	 * スーパークラスのコンストラクタ（DB接続）を実施します
	 * @throws CampusException DB接続に失敗した場合に発生します
	 */
	public StaffDao() throws CampusException {
		super();
	}

	/**
	 * 指定されたIDとパスワードの組み合わせでログイン処理を行います<br>
	 * 組み合わせに該当するスタッフが存在しない場合は例外でログイン不可を通知します
	 * @param staffId スタッフID
	 * @param loginPassword ログインパスワード
	 * @return スタッフ情報
	 * @throws CampusException DB接続もしくはログイン不可の場合に発生します
	 */
	public Staff doLogin(int staffId, String loginPassword)
		throws CampusException {
			// スタッフ情報
			Staff loginUser = null;
			try {
				// 検索実行
				String sql = "SELECT * FROM staff WHERE staff_id = ? AND login_password = ?";
				
				// ★ここにコードを書く★
				ps = con.prepareStatement(sql);
				ps.setInt(1, staffId);
				ps.setString(2, loginPassword);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					String userId = rs.getString("staff_id");
					String userName = rs.getString("staff_name");
					String password = rs.getString("login_password");
					loginUser = new Staff(userId, userName, password);
				}
				// ログイン結果を確認
				if(loginUser == null) {
					throw new CampusException("ログインできませんでした");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new CampusException("SQL実行中に例外が発生しました");
			}
		// スタッフ情報を返却
		return loginUser;
	}
}