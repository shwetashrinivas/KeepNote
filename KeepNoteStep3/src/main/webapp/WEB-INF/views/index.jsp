<!DOCTYPE html>
<html lang="en">
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Keep-Board</title>
</head>
<body>
    <FORM METHOD="POST" ACTION="add" >
   <c:if test="${!errorMessage}">
       <c:out value="${errorMessage}"></c:out>
       </c:if>

<label>NoteTitle</label>
<input type="text" name="noteTitle" ><br>
<label>NoteContent</label>
<input type="text" name="noteContent"><br>
<label>NoteStatus</label>
<select name="noteStatus">
   <option value="active">active</option>
   <option value="inactive">inactive</option>
   </select>
<button type="submit">submit</button>
</FORM>
    <table border="1" cellpadding="5">
        <tr>
        <th>NoteId</th>
        <th>NoteTitle</th>
        <th>NoteContent</th>
        <th>NoteStatus</th>
        <th>Date</th>
        </tr>
        <c:forEach var="user" items="${noteList}">
             <tr>
                 <td>${user.noteId}</td>

                 <td>${user.noteTitle}</td>

                 <td>${user.noteStatus}</td>

                 <td>${user.noteContent}</td>

                 <td>${user.createdAt }</td>

                 <td>
        
             <a href="delete?noteId=${user.noteId}" target="_parent"
                                   class="delete-link">Delete</a>

         <a href="edit?noteId=${user.noteId}" class="modify-link">update</a>
        </td>
             </tr>
         </c:forEach>
        </table>
</body>
</html>



<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<HTML lang="en">
<head>
<title>Keep-Board</title>
</head>
	<!-- Create a form which will have text boxes for Note ID, title, content and status along with a Send 
		 button. Handle errors like empty fields -->
<body> 
		
		<form action='add' method="post">
		
			<c:if test="${!errorMessage.isEmpty()}">
				<p style="color: red;">${errorMessage}</p>
			</c:if>
			
			<label>Note Title:</label> <input type="text" id="noteTitle"
				name="noteTitle" placeholder="note title" class="form-control" /> <br />
			<label>Note Content:</label> <input type="text" id="noteContent"
				name="noteContent" placeholder="note content" class="form-control" />
			<br /> <label>Note Status:</label> <select name="noteStatus">
				<option value="active">active</option>
				<option value="inactive">inactive</option>
			</select> <br /><br />
			<button type="submit">Submit</button>
		</form>

	<!-- display all existing notes in a tabular structure with Id, Title,Content,Status, Created Date and Action -->
	<table>
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
						<form action='delete' method="get">
							<input type="hidden" id="noteId" name="noteId"
								value="${note.noteId}"/>
							<button type="submit">Delete</button>
						</form>
				
				</td>
			</tr>	
							<form action='update' method="post">
								<input type="hidden" id="noteId" name="noteId"
									value="${note.noteId}" class="form-control" /> <label>Note
									Title:</label> <input type="text" id="noteTitle" name="noteTitle"
									placeholder="note title" value="${note.noteTitle}"
									class="form-control" /> <br /> <label>Note Content:</label> <input
									type="text" id="noteContent" name="noteContent"
									placeholder="note content" value="${note.noteContent}"
									class="form-control" /> <br /> <label>Note Status:</label> <select
									name="noteStatus">
									<option value="active">active</option>
									<option value="inactive">inactive</option>
								</select> <br />
								<button type="submit">Update</button>
							</form>
		</c:forEach>

	</table>
</body>
</html>

 --%>