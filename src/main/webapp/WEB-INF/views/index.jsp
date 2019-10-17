<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello ${textHello}</h1>

<form>
    <input type="button" value="open login" onclick="location.pathname='login'"/>
</form>

<form>
    <input type="button" value="open page 'admin'" onclick="location.pathname='admin'"/>
</form>

<form>
    <input type="button" value="open page 'user'" onclick="location.pathname='user'"/>
</form>

<form>
    <input type="button" value="open encodePassword" onclick="location.pathname='encodePassword'"/>
</form>

</body>
</html>
