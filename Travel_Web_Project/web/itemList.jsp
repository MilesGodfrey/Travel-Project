<%-- 
    Document   : itemList
    Created on : May 27, 2021, 5:38:51 PM
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
        <p>
            ${msg}
        </p>
        
       
       
    <p2>Trip Type: ${T.tripType} <br>
        Days: ${T.days} <br>
        Miles: ${T.miles} </p2>
       <br>
       <table border="1" class="list">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Amount</th>
                <th>Cost</th>
                    
            </tr>
            <c:forEach var="i" items="${itemList}">
                <tr>
                    <td align="right">${i.id}</td>
                    <td align="right">${i.name}</td>
                    <td align="right">${i.amount}</td> 
                    <td align="right">${i.cost}</td>
                    
                </tr>
            </c:forEach>
        </table>
       <p>estimated gas total: ${T.gasCost}</p>
       <p>estimated cost: ${T.totalCost}</p> 
       <nav>
 <div id="printPageButton2">
 
 <a class="button" onclick="window.print()">Print</a>
 <a href="Insert.jsp" class="button">Insert New Item</a> 
 <br>

 <form action="delete" method="post" name="delete" id="delete" class="button">
            
               <input name="delete" id="delete" type="text" size="30" style="background-color:#FAFAD2">
    		<input type="submit" value="Delete"  class="submit">
                </form>
                
                
 </div> 
</nav><br>
        <br>
        ${msg}
        <br>
         <a href="Welcome.jsp" class="button">Recalculate</a>
       
        <p>WARNING: changes to the list WILL be required. Everyone needs different supplies so make sure to update and add items that you need.</p>
        

</html>
