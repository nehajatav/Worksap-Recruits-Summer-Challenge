<%-- 
    Document   : feeds
    Created on : Aug 30, 2012, 1:26:26 PM
    Author     : jneha
--%>

<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <span style="float: right">
            <a href="index.jsp" onclick="session.invalidate();">Sign Out</a>
        </span>
        <h1>Welcome <%=request.getParameter("username")%>!</h1>
        <form method="post" action="manageFeeds">
            <input type="hidden" name="username" value="<%=request.getParameter("username")%>"/>
            @<%=request.getParameter("username")%>:<input type="text" size="100" name="feed">
            <input type="submit" name="submit" value="Tweet"/>
        </form>

        <%
            HashMap<Integer,String> tweets = (HashMap<Integer,String>) request.getAttribute("tweets");
            for (Integer key : tweets.keySet()) {
                if(tweets.get(key).startsWith("@"+request.getParameter("username")+" posted at ")){
                    out.println("<form method=\"post\" action=\"manageFeeds\">");
                    out.println("<br/>" + tweets.get(key));
                    out.println("<input type=\"hidden\" name=\"tweetID\" value=\""+key+"\"/>");
                    out.println("<input type=\"submit\" name=\"submit\" value=\"Delete\"/></form>");
                } else{
                    out.println("<br/>" + tweets.get(key));
                }
            }
        %>
    </body>
</html>
