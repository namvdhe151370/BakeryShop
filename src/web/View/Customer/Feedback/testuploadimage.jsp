<%-- 
    Document   : testuploadimage
    Created on : Jul 14, 2022, 10:52:44 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <c:forEach items="${requestScope.listNameIamges}" var="i">
        <img src="/src/uploads/Feedback/${i}">        
        </c:forEach>
         
    </body>
</html>
