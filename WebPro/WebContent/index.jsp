<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
body {
	background-color: #616161 !important;
	font-family: Verdana, sans-serif;
	font-size: 20px;
}

div {
	background-color: #1abc9c;
	color: white;
	margin: auto;
	width: 30%;
	text-align: center
}
</style>
<title>Update & Delete</title>
</head>
<body onload="test()">
	<div>
		<p>
		<form action="Update" method="post">
			<pre>
<p style="color: red">Welcome <%=request.getAttribute("name")%></p>
Username:<input style="color: red" type="text" name="user" id="user"
					value=<%=request.getAttribute("username")%> readonly />
Password:<input style="color: blue" type="password" name="pass"
					id="pass" value=<%=request.getAttribute("password")%> readonly />
Name    :<input type="text" id="name" name="name"
					value=<%=request.getAttribute("name")%> readonly />
Age     :<input type="text" id="age" name="age"
					value=<%=request.getAttribute("age")%> readonly />
Phone No:<input type="text" id="phone" name="phone"
					value=<%=request.getAttribute("phone")%> readonly /><br />
</pre>
			<button type="submit">Update</button>
			<button type="submit" formaction="Delete" method="post">Delete</button>
		</form>
		<br>
		<button id="myButton" onclick="enable()">Edit</button>
		</p>
		<br>
	</div>
	<script>
		function enable() {
			document.getElementById('pass').removeAttribute('readonly');
			document.getElementById('name').removeAttribute('readonly');
			document.getElementById('age').removeAttribute('readonly');
			document.getElementById('phone').removeAttribute('readonly');
			alert('Your are allowed only to change everything except username');
			return false;
		};
		function test() {
			var t =
	<%=request.getAttribute("updated")%>
		;
			if (t == "1") {
				alert("Updated!!!!!");
				return true;
			}
		}
	</script>
</body>
</html>