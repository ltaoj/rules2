<%--
  Created by IntelliJ IDEA.
  User: ltaoj
  Date: 17-6-12
  Time: 上午11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>打卡页面</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/learn/clocktest" method="post">
    <input name="studentId" type="text" placeholder="请输入学号" value="3903150326"/><br/>
    <input name="duration" type="text" placeholder="请输入学习时长" value="10"/><br/>
    <input name="titleNum" type="text" placeholder="请输入刷题数目" value="20"/><br>
    <input name="clockDay" type="datetime" placeholder="系统时间"/><br/>
    <input name="comment" type="text" placeholder="说些感受吧"/><br/>
    <input type="submit" value="打卡">
</form>
</body>
</html>
