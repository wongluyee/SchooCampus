<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Staff" %>

<!-- ナビゲーション -->
<nav class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top">
	<div class="container">

		<!-- ログインページへのリンクを入れています -->
		<a class="navbar-brand" href="login.jsp">SchooCampus</a>

		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<!-- 学生の登録とログアウト用のサーブレットへのリンクを入れています -->
		<!-- リンクをクリックすると各サーブレットのdoGetメソッドが実行されます -->
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active">
					<a class="nav-link" href="StudentServlet">学生の登録</a>
				</li>
				<li class="nav-item active">
					<a class="nav-link" href="LoginServlet">ログアウト</a>
				</li>
			</ul>
			<div class="navbar-text">
				ログインユーザ名：<%=((Staff)session.getAttribute("staff")).getStaffName() %>
			</div>
		</div>
	</div>
</nav>