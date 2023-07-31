<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Staff" %>
<!DOCTYPE html>
<html>
	<!-- 共通パーツ（ヘッダー）読み込み -->
	<jsp:include page="common/header.jsp"/>
	<body>
		<!-- 共通パーツ（ナビ）読み込み -->
		<jsp:include page="common/navi.jsp"/>
		<div class="login align-items-center py-5">
			<div class="container">
				<div class="row">
					<div class="col-md-9 col-lg-8 mx-auto">
						<h3 class="login-heading my-4">Login</h3>

						<!-- ログイン・ログアウト結果の表示処理 -->
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
						<form action="LoginServlet" method="post">
							<!-- ログインID -->
							<div class="form-label-group">
								<label for="id">Login ID</label>
								<input type="text" id="id"
									name="loginId"
									class="form-control">
							</div>
							<!-- パスワード -->
							<div class="form-label-group">
								<label for="password">Password</label>
								<input type="password" id="password"
									name="loginPassword"
									class="form-control">
							</div>
							<button class="btn btn-lg btn-primary btn-block btn-login font-weight-bold my-2"
								type="submit">
								ログイン
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- 共通パーツ（フッター）読み込み -->
		<jsp:include page="common/footer.jsp"/>
	</body>
</html>