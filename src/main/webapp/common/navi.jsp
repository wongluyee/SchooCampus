<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Staff" %>

<!-- ナビゲーション -->
<nav class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top">
	<div class="container">

		<!-- ログインページへのリンクを入れています -->
		<a class="navbar-brand" href="login.jsp">SchooCampus</a>

		<!-- ログアウト用のサーブレットへのリンクを入れています -->
		<!-- リンクをクリックすると各サーブレットのdoGetメソッドが実行されます -->
		<%
			Staff staff = (Staff)session.getAttribute("staff");
			if(staff != null) {
		%>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active">
						<a class="nav-link" href="StudentServlet?list=true">学生の一覧</a>
					</li>
					<li class="nav-item active">
						<a class="nav-link" href="LoginServlet">ログアウト</a>
					</li>
				</ul>
				<div class="navbar-text">
					ログインユーザ名：<%=((Staff)session.getAttribute("staff")).getStaffName() %>
				</div>
			</div>
		<% } %>
	</div>
</nav>