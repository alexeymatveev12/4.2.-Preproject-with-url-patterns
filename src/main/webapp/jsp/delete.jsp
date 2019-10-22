
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Delete User Page</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>

<div align="center">

    <table bordercolor="red" border="1" cellpadding="4" cellspacing="0">
        <caption>
            <h2>Delete page</h2>
        </caption>
        <br/><br/>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Login</th>
            <th>Password</th>
            <th>User's role</th>
            <th>Confirmation - Delete the record</th>
        </tr>

        <jsp:useBean id="user" scope="request" type="model.User"/>


            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.password}"/></td>
                <td><c:out value="${user.role}"/></td>
                <td>
                    <form method="POST" action="/admin/delete?id=<c:out value='${user.id}' />">
                        <input type="submit" value="Delete this User"/>
                    </form>

                </td>

            </tr>



    </table>
    <hr>

    <br/><br/>
    <hr>



    <form action="/admin/create">
        <input type="submit" value="Create the New User" />
    </form>

    <form action="/admin/read">
        <input type="submit" value="Read User's List" />
    </form>


</div>


</div>
</body>
</html>
