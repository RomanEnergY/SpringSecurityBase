<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>encodePassword</title>
</head>
<body>

<form>
    <h2>Внести данные таблицу User/Password</h2>
    <input type="textarea" id="p1" name="Password.user" placeholder="User" cols="20" rows="1"/>
    <input type="textarea" id="p2" name="Password.password" placeholder="Password" cols="20" rows="1"/>
    <input type="button" id="bt" value="create" onclick="location.pathname='encodePassword/' + p1.value + '/' + p2.value"/>
</form>
<p>User: ${user}</p>
<p>Password: ${encodePassword}</p>

</body>
</html>
