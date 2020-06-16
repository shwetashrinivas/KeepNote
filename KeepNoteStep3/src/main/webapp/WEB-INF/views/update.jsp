<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%> --%>

<!DOCTYPE html>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<FORM METHOD="post" ACTION="update" >
<c:if test="${!errorMessage}">
<c:out value="${errorMessage}"></c:out>
</c:if>

<input type="hidden" name="noteId" value="${noteList.noteId}"><br>
<label>NoteTitle</label>
<input type="text" name="noteTitle" value="${noteList.noteTitle}"><br>
<label>NoteContent</label>
<input type="text" name="noteContent" value="${noteList.noteContent}"><br>
<label>NoteStatus</label>
<select name="noteStatus">
   <option value="active">active</option>
   <option value="inactive">inactive</option>
</select>
<button type="submit">submit</button>
</FORM>
</body>
</html>