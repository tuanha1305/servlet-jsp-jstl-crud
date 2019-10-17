<%@ page import="io.github.tuanictu97.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="tuanictu97" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<meta charset="utf-8">
<title>Edit</title>
</head>
<body>
	<% Student st = (Student) request.getAttribute("st"); %>
	<div class="container">
		<form method="post" action="<%= request.getContextPath()%>/StudentServlet?action=doEdit">
			<div class="form-group">
		    <label for="idInput">ID</label>
		    <input type="text" name="idst" class="form-control" value="<%= st.getId() %>" readonly="readonly" id="idInput" placeholder="Enter your name">
		  </div>
		  <div class="form-group">
		    <label for="nameInput">Name</label>
		    <input type="text" name="name" class="form-control" value="<%= st.getName() %>" id="nameInput" placeholder="Enter your name">
		  </div>
		  <div class="form-group">
		    <label for="addressInput">Address</label>
		    <input type="text" name="address" class="form-control" value="<%= st.getAddress() %>"  id="addressInput" placeholder="Enter your address">
		  </div>
		  <button type="submit" class="btn btn-primary">Edit</button>
		</form>
	</div>
</body>
</html>