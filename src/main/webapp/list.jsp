<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Student" %>
<!DOCTYPE html>
<html>
	<!-- 共通パーツ（ヘッダー）読み込み -->
	<jsp:include page="common/header.jsp"/>
	<body>
		<!-- 共通パーツ（ナビ）読み込み -->
		<jsp:include page="common/navi-list.jsp"/>
		<!-- 一覧表示 -->
		<section class="py-5">
			<div class="container">
				<h1 class="my-4">学生一覧</h1>
				<!-- 学生一覧 -->
				<table class="table table-hover">
					<!-- 見出し部分 -->
					<thead class="thead-dark">
						<tr>
							<th scope="col">学生番号</th>
							<th scope="col">学生名</th>
							<th scope="col">詳細</th>
						</tr>
					</thead>
					<!-- 一覧部分 -->
					<!-- データベースから取得した学生の情報一覧をリクエストスコープから取得します -->
					<!-- eclipseが未検査キャストの警告を出しますが無視して大丈夫です -->
					<tbody>
					<% List<Student> studentList = (List<Student>)request.getAttribute("studentList"); %>
					<% for(Student student : studentList) { %>
						<tr>
							<td><%= student.getStudentNumber() %></td>
							<td><%= student.getStudentName() %></td>
							<td>
								<a class="btn btn-primary"
								href="StudentServlet?student_number=<%= student.getStudentNumber() %>">詳細</a>
							</td>
						</tr>
					<% } %>
					</tbody>
				</table>
			</div>
		</section>
		<!-- 共通パーツ（フッター）読み込み -->
		<jsp:include page="common/footer.jsp"/>
	</body>
</html>