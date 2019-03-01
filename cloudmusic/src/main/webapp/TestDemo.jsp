
<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2019/1/27
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/cloudmusic/UseServlet" method="post">
    <input type="hidden" name="method" value="regist"/>
    用户名：<input type="text" name="uid"><br/>
    密&nbsp;&nbsp;&nbsp;码：<input type="password" name="password"><br/>
    <input type="submit" value="注册">
</form>
</body>
</html>
