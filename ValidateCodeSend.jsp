<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mail Register</title>
</head>
<body>
<div >
    <form action="<%=request.getContextPath()%>/SendMailServlet " method="post">
        <table>
            <td>E-mail:<input type="email" id="email" name="usermail"></td>
        </tr>
            <tr>
                <td>Password:<input type="password" name="userpsw" ></td>
            </tr>
                <tr>
                    <td>
                        <input type="submit" value="发送邮箱验证码"  >
                    </td>
                </tr>
        </table>
    </form>
</div>
