<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Set" %>
<%@ page import="org.example.model.User" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
</head>
<body>

<h2>User List</h2>

<table border="1">
    <thead>
        <tr>
            <th>Name</th>
            <th>Email</th>
        </tr>
    </thead>
    <tbody>
        <%
            Set<User> users = (Set<User>) request.getAttribute("users");
            if (users == null || users.isEmpty()) {
        %>

        <tr>
            <td colspan="2">No users found.</td>
        </tr>

        <%
            } // if
        %>

        <%
            for (User user : users) {
        %>

        <tr>
            <td><%= user.name() %></td>
            <td><%= user.email() %></td>
        </tr>

        <%
            } // for
        %>

    </tbody>
</table>

</body>
</html>