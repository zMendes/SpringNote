<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Adicionar nota</title>
</head>
<body>
	<%
		String id = session.getAttribute("id").toString();
	%>
	<form action="addNote" method='post'>
		Nota: <br>
		<textarea name="content" rows="6" cols="80">
	</textarea> <br>
		<input type="hidden" name="id" value="<%=id%>"> <input
			type="submit" value='Submit' />
	</form>
</body>
</html>