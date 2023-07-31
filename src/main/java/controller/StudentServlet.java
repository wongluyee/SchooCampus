package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StudentDao;
import exception.CampusException;
import model.Staff;
import model.Student;
import model.StudentMemo;

/**
 * 学生情報画面のサーブレットクラス
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	/**
	 * ログイン処理後に連動して動きます<br>
	 * LoginServletがdoPostからの転送になるのでこちらもdoPostで動かしています
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nextPage = null;
		try {
			// 学生一覧の取得
			// ★ここにコードを書く★
			StudentDao studentDao = new StudentDao();
			List<Student> studentList = studentDao.findAllStudent();

			// 取得した学生一覧をリクエストスコープにセット
			// ★ここにコードを書く★
			request.setAttribute("studentList", studentList);

			// 一覧画面を表示する準備
			nextPage = "list.jsp";

		} catch (CampusException e) {
			String message = e.getMessage();
			request.setAttribute("message", message);
			request.setAttribute("error", "true");
			// ログイン画面を表示する準備
			nextPage = "login.jsp";
		}

		// 次の画面に遷移
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

	/**
	 * 今回詳細画面（新規登録も含む）を表示するために使います
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// おまけ機能（一覧に戻る）を判定
		// リクエストがなければ詳細画面を表示する準備
		String list = request.getParameter("list");
		if (list == null) {

			// 詳細画面に表示する学生番号をリクエストパラメータから取得
			// リクエストURLのクエリストリングに学生番号が表示されています
			String findStudentNumber = request.getParameter("student_number");

			// ログインしている職員の情報を取得
			// DBの登録（更新）処理の際に使用します
			HttpSession session = request.getSession(false);
			// ★ここにコードを書く★
			Staff staff = (Staff) session.getAttribute("staff");
			StudentMemo studentMemo = null;

			try {
				if (findStudentNumber != null) {
					// 学生番号が指定されている場合は詳細表示（更新）
					// 学生番号に紐づく情報を検索してセット
					// ★ここにコードを書く★
					StudentDao studentDao = new StudentDao();
					studentMemo = studentDao.findStudentMemo(findStudentNumber);

				} else {
					// 学生番号が指定されていない場合は新規登録
					// スタッフの情報だけセット（その他の情報は空）
					String staffId = staff.getStaffId();
					String staffName = staff.getStaffName();
					studentMemo = new StudentMemo(staffId, staffName);
				}
				// 次の画面に情報をセット
				request.setAttribute("studentMemo", studentMemo);

			} catch (CampusException e) {
				e.printStackTrace();
				String message = e.getMessage();
				request.setAttribute("message",  message);
				request.setAttribute("error",  "true");
			}
			// 次の画面に遷移
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("detail.jsp");
			requestDispatcher.forward(request, response);

		} else {
			// backのリクエストがある場合は一覧画面を表示
			// 今回はdoPostメソッドに処理を移譲するようにしています
			doPost(request, response);
		}
	}
}