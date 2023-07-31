package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StaffDao;
import exception.CampusException;
import model.Staff;

/**
 * ログイン画面のサーブレットクラス
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	/**
	 * このサーブレットのdoPostメソッドはログイン用に使用します
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		int loginId = Integer.parseInt(request.getParameter("loginId"));
		String loginPassword = request.getParameter("loginPassword");
		String nextPage = null;
		try {
			// ログイン処理（DBにIDとパスワードの組み合わせがある職員がいるかチェック）
			
			// ★ここにコードを書く★
			StaffDao staffDao = new StaffDao();
			Staff staff = staffDao.doLogin(loginId, loginPassword);

			// ログインできている場合はログインした職員の情報をセッションにセット
			// ログイン状態とみなします
			HttpSession session = request.getSession();
			
			// ★ここにコードを書く★
			session.setAttribute("staff",  staff);

			// 学生一覧の表示へ遷移する準備
			nextPage = "StudentServlet";

		} catch (CampusException e) {
			// ログインできなかった場合はメッセージをセットしてlogin.jspに表示
			String message = e.getMessage();
			request.setAttribute("message",  message);
			request.setAttribute("error",  "true");
			// ログイン画面へ遷移する準備
			nextPage = "login.jsp";
		}

		// 次の画面に遷移
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

	/**
	 * このサーブレットのdoGetメソッドはログアウト用に使用する想定です
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		// セッションの情報を破棄
		HttpSession session = request.getSession(false);
		session.invalidate();

		// login.jspに表示するメッセージをセット
		request.setAttribute("message", "ログアウトしました");

		// login.jspを表示
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
		requestDispatcher.forward(request, response);
	}
}