<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
          Library: Home
		  <h2>You are logged in as:	<s:property value = "userType"/> <s:property value ="#session.currentUser"/></h2>
		  <h3>Add a book below:</h3>
		  <s:form action="addBook" >
            <s:textfield name="bookTitle" label="Book title: "/>
			<s:textfield name="bookAuthor" label="Author: "/>
			<s:textfield name="isbnNum" label="ISBN number: "/>
            <s:submit />
        </s:form>
        
		
    </body>
</html>