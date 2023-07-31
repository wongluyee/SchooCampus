<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.StudentMemo" %>
<!DOCTYPE html>
<html>
	<!-- 共通パーツ（ヘッダー）読み込み -->
	<jsp:include page="common/header.jsp"/>
	<body>
		<!-- 共通パーツ（ナビ）読み込み -->
		<jsp:include page="common/navi.jsp"/>
		<!-- 詳細部分 -->
		<section class="py-5">
			<div class="container">
				<h1 class="my-4">学生詳細</h1>
				<!-- DBを新規登録または更新した結果の表示処理 -->
				<!-- サーブレットでメッセージの設定処理を入れていない場合でも影響はありません -->
				<%
					// 処理メッセージとエラー判定を取得
					String message = (String)request.getAttribute("message");
					String error = (String)request.getAttribute("error");

					// 正常終了した場合のメッセージを表示
					if(message != null && error == null) { %>
						<div class="alert alert-success" role="alert">
							<%= message %>
						</div>
				<%
					// 異常終了した場合のメッセージを表示
					} else if(message != null && error != null) { %>
						<div class="alert alert-danger" role="alert">
							<%= message %>
						</div>
				<% } %>

				<!-- 学生の詳細情報（新規登録の場合は空白を表示） -->
				<%
					// サーブレットから詳細の情報を取得（新規登録の場合はすべて空文字）
					StudentMemo studentMemo = (StudentMemo)request.getAttribute("studentMemo");
				%>
				<div class="row justify-content-center">
					<div class="media-container-column col-lg-8">
						<form action="StudentMemoServlet" method="post">
							<div class="row">
								<div class="col-md-12 form-group">
									<label class="form-control-label">学生番号</label>
									<input type="text"
										name="student_number"
										value="<%=studentMemo.getStudentNumber() %>"
										required="required"
										class="form-control">
								</div>
								<div class="col-md-12 form-group">
									<label class="form-control-label">学生名</label>
									<input type="text"
										name="student_name"
										value="<%=studentMemo.getStudentName() %>"
										required="required"
										class="form-control">
								</div>
								<div class="col-md-12 form-group">
									<label class="form-control-label">メモ欄</label>
									<textarea name="memo" class="form-control display-7"><%=studentMemo.getMemo() %></textarea>
								</div>
								<div class="col-md-12 form-group">
									<label class="form-control-label">最後の更新者</label>
									<p><%=studentMemo.getStaffName() %></p>
								</div>
								<div class="col-md-12 align-center">
									<button type="submit" class="btn btn-primary btn-form display-4">登録</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</section>
		<!-- 共通パーツ（フッター）読み込み -->
		<jsp:include page="common/footer.jsp"/>
	</body>
</html>