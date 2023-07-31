package controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StudentDao;
import exception.CampusException;
import model.Staff;
import model.StudentMemo;

/**
 * 学生詳細画面のサーブレットクラス
 */
@WebServlet("/StudentMemoServlet")
public class StudentMemoServlet extends HttpServlet {
	/**
	 * 学生情報およびメモ情報の登録・更新処理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		// セッションからスタッフ情報を取得
		HttpSession session = request.getSession(false);
		Staff staff = (Staff)session.getAttribute("staff");
		String staffId = staff.getStaffId();
		String staffName = staff.getStaffName();

		// 画面に表示（入力）された学生番号、学生名、メモを取得
		request.setCharacterEncoding("UTF-8");
		// ★ここにコードを書く★
		String studentNumber = request.getParameter("student_number");
		String studentName = request.getParameter("student_name");
		String memo = request.getParameter("memo");

		String message = null; // 処理後に画面に表示するメッセージ
		try {
			// 学生番号でメモテーブルを検索
			// メモIDがある場合は更新、無い場合（戻り値が0の場合）は新規登録
			// ★ここにコードを書く★
			StudentDao studentDao = new StudentDao();
			String memoId = studentDao.findMemoId(studentNumber);

			// DAOクラスに渡すために学生メモ情報クラスに値を格納
			StudentMemo studentMemo =
					new StudentMemo(studentNumber, studentName, staffId, staffName, memoId, memo);

			if(memoId == null) {
				// 新規登録
				// ★ここにコードを書く★
				studentDao.insertStudent(studentMemo);
				message = "学生の情報を登録しました";
			} else {
				// 更新
				studentDao.updateMemo(studentMemo, memoId);
				message = "メモを更新しました";
			}
			// 新規登録または更新した情報を再度画面に表示
			request.setAttribute("studentMemo", studentMemo);

		} catch (CampusException e) {
			e.printStackTrace();
			message = e.getMessage();
			request.setAttribute("error",  "true");
		}
		// 次の画面に遷移
		request.setAttribute("message", message);
		request.getRequestDispatcher("detail.jsp").forward(request, response);
	}
}