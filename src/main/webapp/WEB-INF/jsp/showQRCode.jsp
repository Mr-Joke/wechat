<%--
  Created by IntelliJ IDEA.
  User: G110370
  Date: 2017/8/23
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>扫码关注</title>
</head>
<body>
    <div style="margin: auto">
        <img src="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=${json.ticket}">
    </div>
</body>
</html>
