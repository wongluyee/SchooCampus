package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.CampusException;

/**
 * DAOクラスの共通処理をまとめたスーパークラス<br>
 * abstractは抽象クラスでこのクラス単体では使えません<br>
 * 必ずサブクラス（継承）して使用します
 */
public abstract class BaseDao {

	protected Connection con = null;
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;

	/**
	 * コンストラクタ<br>
	 * 初期処理としてDBに接続します
	 * @throws CampusException DB接続に失敗した際に発生します
	 */
	public BaseDao() throws CampusException {
		// DBに接続
		getConnection();
	}

	/**
	 * DB接続処理
	 * @throws CampusException DB接続に失敗した際に発生します
	 */
	private void getConnection() throws CampusException {
		try {
			if (con == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				// （注意）接続するDB名、ユーザ名、パスワードはご自身の環境に合わせて設定してください！
				String url  = "jdbc:mysql://localhost/campus?characterEncoding=UTF-8&serverTimezone=JST";
				String user = "root";
				String password = "password";
				// DB接続
				con = DriverManager.getConnection(url, user, password);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new CampusException("JDBCドライバが見つかりません");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CampusException("SQL実行中に例外が発生しました");
		}
	}

	/**
	 * DBとの接続を解除します
	 * @throws CampusException
	 */
	protected void close() throws CampusException {
		try {
			if (con != null) {
				con.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			throw new CampusException("close処理中に例外が発生しました");
		}
	}
}