<%@ page contentType="text/html; ISO-8859-1" language="java"%>
<!DOCTYPE html PUBLIC "-//w3c//DTD HTML 4.01 Transitional//En"
                      "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Login Success page</title>
</head>
<body>
    <h3> Hi <%= request.getAttribute("user")  %>, Login successful.</h3>
    <a href="login.html">Login Page</a>
</body>
</html>
