package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.CampusException;
import model.Student;
import model.StudentMemo;

/**
 * 学生情報関連のDAOクラス
 */
public class StudentDao extends BaseDao {
	/**
	 * コンストラクタ<br>
	 * スーパークラスのコンストラクタ（DB接続）を実施します
	 * @throws CampusException DB接続に失敗した場合に発生します
	 */
	public StudentDao() throws CampusException {
		super();
	}

	/**
	 * 学生テーブルに登録されているすべての学生情報を検索します<br>
	 * 検索結果が0件の場合は空のリストを返却します
	 * @return 学生情報の入ったリスト
	 * @throws CampusException 検索失敗の際に発生します
	 **/
	public List<Student> findAllStudent() throws CampusException {
		// 学生情報のリスト
		ArrayList<Student> studentList = new ArrayList<>();
		try {
			// SQL文
			String sql = "SELECT * FROM student";

			// 検索の実行
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// 検索結果から学生番号と学生名を取得してリストに格納
			while(rs.next()) {
				String studentNumber = rs.getString("student_number");
				String studentName = rs.getString("student_name");
				Student student = new Student(studentNumber, studentName);
				studentList.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CampusException("学生情報の取得に失敗しました");
		}

		// 学生情報のリストを返却
		return studentList;
	}

	/**
	 * 学生テーブルから指定された学生番号で登録されている学生情報を検索します<br>
	 * 検索結果が0件の場合はnullを返却します
	 * @param findStudentNumber 学生番号
	 * @return 学生情報
	 * @throws CampusException 検索失敗の際に発生します
	 */
	public Student findStudent(String findStudentNumber) throws CampusException {
		// 学生情報
		Student student = null;
		try {
			// SQL文
			String sql = "SELECT * FROM student WHERE student_number = ?";

			// SQL文に学生番号をセットして検索を実行
			ps = con.prepareStatement(sql);
			ps.setString(1, findStudentNumber);
			rs = ps.executeQuery();

			// 検索結果から学生情報を作成
			while(rs.next()) {
				String studentNumber = rs.getString("student_number");
				String studentName = rs.getString("student_name");
				student = new Student(studentNumber, studentName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CampusException("指定された学生情報の取得に失敗しました");
		}
		// 学生情報を返却
		return student;
	}

	/**
	 * メモおよび学生テーブルから指定された学生番号で登録されている学生メモ情報を検索します<br>
	 * 検索結果が0件の場合はnullを返却します
	 * @param findStudentNumber 学生番号
	 * @return 学生メモ情報
	 * @throws CampusException 検索失敗の際に発生します
	 */
	public StudentMemo findStudentMemo(String findStudentNumber) throws CampusException {
		// 学生メモ情報
		// このクラスは学生情報とメモ情報を管理しています
		StudentMemo studentMemo = null;
		try {
			// SQL文
			String sql =
				"SELECT "	// 検索内容
				+ "student.student_number, "
				+ "student.student_name, "
				+ "staff.staff_id, "
				+ "staff.staff_name, "
				+ "memo.memo_id, "
				+ "memo.memo "
				+ "FROM "	// 検索対象（JOINで結合）
				+ "memo JOIN "
				+ "student ON memo.student_number = student.student_number JOIN "
				+ "staff ON memo.updated_staff_id = staff.staff_id "
				+ "WHERE "	// 検索条件
				+ "memo.student_number = ?";

			// SQL文に学生番号をセットして検索を実行
			ps = con.prepareStatement(sql);
			ps.setString(1, findStudentNumber);
			rs = ps.executeQuery();

			// 検索結果から学生メモ情報を作成
			while(rs.next()) {
				String studentNumber = rs.getString("student_number");
				String studentName = rs.getString("student_name");
				String staffId = rs.getString("staff_id");
				String staffName = rs.getString("staff_name");
				String memoId = rs.getString("memo_id");
				String memo = rs.getString("memo");
				studentMemo =
					new StudentMemo(studentNumber, studentName, staffId, staffName, memoId, memo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CampusException("指定された学生メモ情報の取得に失敗しました");
		}
		// 学生メモ情報を返却
		return studentMemo;
	}

	/**
	 * 指定された学生メモ情報をDBに新規追加します<br>
	 * 登録先は学生テーブルとメモテーブルです
	 * @param studentMemo 学生メモ情報
	 * @throws CampusException 学生メモ情報の登録に失敗した際に発生します
	 */
	public void insertStudent(StudentMemo studentMemo) throws CampusException {
		// 学生メモ情報からDBに登録する値を取得
		String studentNumber = studentMemo.getStudentNumber();	// 学生番号
		String studentName = studentMemo.getStudentName();	// 学生名
		String staffId = studentMemo.getStaffId();	// スタッフID
		String memo = studentMemo.getMemo();	// メモ

		try {
			// 学生テーブルへの追加
			String sql = "INSERT INTO student(student_number, student_name) VALUE(?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, studentNumber);
			ps.setString(2, studentName);
			ps.executeUpdate();

			// メモテーブルへの追加
			sql = "INSERT INTO memo(student_number, updated_staff_id, memo) VALUE(?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, studentNumber);
			ps.setString(2, staffId);
			ps.setString(3, memo);
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			throw new CampusException("学生情報の登録に失敗しました");
		}
	}

	/**
	 * 指定されたメモ情報を更新します<br>
	 * 今回は更新対象はメモのみにしています
	 * @param studentMemo 更新情報
	 * @param memoId 更新対象とするメモのID
	 * @throws CampusException メモ情報の更新に失敗した際に発生します
	 */
	public void updateMemo(StudentMemo studentMemo, String memoId) throws CampusException {
		// メモとスタッフを取得
		String memo = studentMemo.getMemo();
		String staffId = studentMemo.getStaffId();
		try {
			// 更新処理 (memo & staff)
			String sql = "UPDATE memo SET memo = ?, updated_staff_id = ? WHERE memo_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, memo);
			ps.setString(2, staffId);
			ps.setString(3, memoId);
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			throw new CampusException("メモの更新に失敗しました");
		}
	}

	/**
	 * メモテーブルから指定された学生番号に該当するメモIDを取得します<br>
	 * 検索結果が無い場合はnullを返却します
	 * @param studentNumber 学生番号
	 * @return メモID
	 * @throws CampusException メモIDの取得に失敗した場合に発生します
	 */
	public String findMemoId(String studentNumber) throws CampusException {
		// メモID
		String memoId = null;
		try {
			// メモIDの検索
			String sql = "SELECT memo_id FROM memo WHERE student_number = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, studentNumber);
			rs = ps.executeQuery();
			while(rs.next()) {
				memoId = rs.getString("memo_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CampusException("メモIDの取得に失敗しました");
		}
		// メモIDの返却
		return memoId;
	}
}