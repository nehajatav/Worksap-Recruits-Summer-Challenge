<%-- 
    Document   : index
    Created on : Aug 28, 2012, 1:55:53 PM
    Author     : jneha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Twitter</title>
    </head>
    <body>
        <h1>Welcome to Twitter</h1>
        <ul></ul>
        <%
            session.invalidate();
        %>
        <%
            if (request.getParameter("error") != null) {
                if (request.getParameter("error").equals("login")) {
                    out.println("<font color=\"red\">Login/password incorrect</font><br/>");
                }
            }
        %>
        <h3>Existing users:</h3>
        <form method="post" action="checkLogin">
            Username: <input type="text" name="username"/><br/>
            Password: <input type="password" name="password" /><br/>
            <input type="submit" name="submit" value="Login"/>
        </form>

        <%
            if (request.getParameter("error") != null) {
                if (request.getParameter("error").equals("password")) {
                    out.println("<br/><font color=\"red\">Passwords did not match/Null password</font><br/>");
                } else if (request.getParameter("error").equals("username")) {
                    out.println("<br/><font color=\"red\">Username already exists</font><br/>");
                }
            }
        %>
        <h3>New Users:</h3>
        <form method="post" action="checkLogin">
            <input type="hidden" name="purpose" value="new" />
            Username: <input type="text" name="username"/><br/>
            Password: <input type="password" name="password" /><br/>
            Repeat Password: <input type="password" name="repeat" /><br/>
            <input type="submit" name="submit" value="Create"/>
        </form>
    </body>
</html>
