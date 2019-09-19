<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, mvc.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Suas notas</title>
</head>
<body>
	<%
		List<Note> notes;
		User user = new User();
		UserDAO dao = new UserDAO();
		int id = Integer.parseInt(session.getAttribute("Logado").toString());
		user.setId(id);
		if (session.getAttribute("sort") != null) {
			if (session.getAttribute("sort".toString()) == "chronological") {
				notes = dao.getNotesChronological(user).getContent();
			} else if (session.getAttribute("sort").toString() == "alphabetical") {
				notes = dao.getNotesAlphabetical(user).getContent();
			} else {
				notes = dao.getNotes(user).getContent();
			}
		} else {
			notes = dao.getNotes(user).getContent();
		}
	%>
	Bem vindo, suas notas:
	<br> Sort by:
	<form action="alphabetical">
		<input type="submit" value="alphabetical">
	</form>
	<form action="chronological">
		<input type="submit" value="chronological">
	</form>
	<table border='2'>
		<tr>

			<td><b>Nota</b></td>
			<td><b>Editar</b></td>
			<td><b>Remover</b></td>
		</tr>
		<%
			for (Note note : notes) {
		%>
		<tr>
			<td><%=note.getContent()%></td>
			<td>
				<form action="alter" method='post'>
					<input type="hidden" value=<%=note.getNoteId()%> name="noteId">
					<input type="submit" value="                 ">
				</form>
			</td>
			<td>
				<form action="remove" method='post'>
					<input type="hidden" value=<%=note.getNoteId()%> name="noteId">
					<input type="submit" value="                 ">
				</form>
			</td>
		</tr>
		<%
			}
		%>
	</table>

	<form action="add" method='post'>
		<input type='hidden' name="id" value='<%=id%>'> <input
			type="submit" value="Adicionar nota">
	</form>
	<a href="logout">Logout</a>
</body>
</html>