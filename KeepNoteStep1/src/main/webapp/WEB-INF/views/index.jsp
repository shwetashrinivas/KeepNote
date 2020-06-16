
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KeepNote</title>
</head>
<body>
	<!-- Create a form which will have text boxes for Note ID, title, content and status along with a Send 
		 button. Handle errors like empty fields -->

	<!-- <form action='saveNote' method="post" modelAttribute="note"> -->

	<form action='saveNote' method="post">
	<c:if test="${!errorMessage}">
		<c:out value="${errorMessage}"></c:out>
		</c:if>

		<label>Note Id:</label> <input type="text" id="noteId" name="noteId"
			class="form-control" /> <br /> <label>Note Title:</label> <input
			type="text" id="noteTitle" name="noteTitle" class="form-control" />
		<br /> <label>Note Content:</label> <input type="text"
			id="noteContent" name="noteContent" class="form-control" /> <br />
		<label>Note Status:</label> <select name="noteStatus">
			<option value="active">active</option>
			<option value="inactive">inactive</option>
		</select> <br />

		<button type="submit" class="form-control">Submit</button>

	</form>
		

	<!-- display all existing notes in a tabular structure with Id, Title,Content,Status, Created Date and Action -->
	<table border="2" cellpadding="15">
		<c:forEach items="${noteList}" var="note">
			<tr>
				<td>Note Id :</td>
				<td>${note.noteId}</td>
			</tr>
			<tr>
				<td>Note Title :</td>
				<td>${note.noteTitle}</td>
			</tr>
			<tr>
				<td>Note Content :</td>
				<td>${note.noteContent}</td>
			</tr>
			<tr>
				<td>Note Status :</td>
				<td>${note.noteStatus}</td>
			</tr>
			<tr>
				<td>Note created at :</td>
				<td>${note.createdAt}</td>
			</tr>
			<tr>
				<td>
					<form action='deleteNote' method="get">
						<input type="hidden" id="noteId" name="noteId"
							value="${note.noteId}" class="form-control" />
						<button type="submit" class="form-control">Delete</button>
					</form>

				</td>
			</tr>

		</c:forEach>

	</table>
</body>
</html>