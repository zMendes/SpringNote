<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, mvc.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alterar nota</title>
</head>
<body>
	<%
		String nota = session.getAttribute("nota").toString();
		int noteId = Integer.parseInt(session.getAttribute("noteId").toString());
	%>
	<form action="edit" method='post'>
		Nota: <br>
		<textarea name="content" rows="6" cols="80"><%=nota%>
	</textarea> <br>
		<input type="hidden" name="noteId" value="<%=noteId%>"> <input
			type="submit" value='Submit' />
	</form>
</body>
</html>