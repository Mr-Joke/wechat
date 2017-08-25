<%--
  Created by IntelliJ IDEA.
  User: G110370
  Date: 2017/8/25
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传图片</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/material/upload" method="post" enctype="multipart/form-data">
    <table align="center">
        <caption>上传图片</caption>
        <tr>
            <td>用户编号：</td>
            <td><input type="text" name="user_id"/></td>
        </tr>
        <tr>
            <td>选择图片：</td>
            <td><input type="file" name="filename"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="上传"/></td>
            <td><input type="reset" value="重置"/></td>
        </tr>
    </table>
</form>
</body>
</html>
