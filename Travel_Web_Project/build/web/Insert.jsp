<%-- 
    Document   : Insert
    Created on : Jun 22, 2021, 3:19:51 PM
    Author     : miles
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="TravelStyles.css" type="text/css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Insert Item information <br>  </h1>
        <p>${msg}</p>
        <form action="insert" method="post" name="insert" id="insert">
      <table width="600px" border="0" class="prompt">
  		<tr>
    		<td>  </td>
    		<td>Name</td>
    		<td>Amount needed per day</td>
                <td>Cost per day</td> 
  		</tr>
  		<tr>
                <td> 
                ID: ${I.id}
                </td>
    		<td><input name="name" id="name" type="text" size="30" style="background-color:#FAFAD2"
                           value=""></td>
    		<td><input name="amount" id="amount" type="text" size="30" style="background-color:#FAFAD2"
                           value=""></td>
                <td><input name="cost" id="cost" type="text" size="30" style="background-color:#FAFAD2"
                           value=""></td>
  		</tr>
		</table><br />
        
                <p>Select trip types</p>
                <input type="checkbox" id="choice1" name="choice1">
                <label for="choice1"> Regular </label><br>
                <input type="checkbox" id="choice2" name="choice2">
                <label for="choice2"> Long Distance </label><br>
                <input type="checkbox" id="choice3" name="choice3">
                <label for="choice3"> Hunting </label><br> 
                <input type="checkbox" id="choice4" name="choice4">
                <label for="choice4"> Kayak </label><br>
                
        <tr>
             
                
                 <a href="Welcome.jsp" class="button">Recalculate</a>
                 
                 <input class="button" type="submit" value="insert">
                </form>
        
        
    </body>
</html>
