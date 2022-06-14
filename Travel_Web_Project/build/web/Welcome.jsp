
<!DOCTYPE HTML >

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <link rel="stylesheet" href="TravelStyles.css" type="text/css">
        <title>Travel App</title>
    </head>
    <body>
    
        <h1>The Packing List App</h1><br/>
        <p>${msg}</p>
      <form action="travel" method="post" name="travel" id="travel">
      <table id="table1" width="600px" border="0" class="prompt">
  		<tr>
    		<td>Travel Type</td>
    		<td>Amount of Miles</td>
    		<td>Days</td>
  		</tr>
  		<tr>
                <td> 
                    <select name ="type" >
                    <option value = "Regular"> Regular   
                    </option>  
                    <option value = "Long Distance"> Long Distance   
                    </option>  
                    <option value = "Hunting"> Hunting
                    </option>  
                    <option value = "Kayak"> Kayak 
                    </option>  
                    </select>  
                   
                    
                </td>
    		<td><input name="miles" id="miles" type="text" size="30" style="background-color:#FAFAD2"
                           value="${travel.miles}"></td>
    		<td><input name="days" id="days" type="text" size="30" style="background-color:#FAFAD2"
                           value="${travel.days}"></td>
  		</tr>
		</table><br />
        
        <tr>
            <td> <input type="submit" value="Calculate"  class="button">
                </form>
            </td>
        <td></td>
        <td></td>
        
    
        
        </table>
        <br>
        <%    
            String msg = (String) request.getAttribute("msg");
            if (msg != null){
                %>
                <p> Errors: <%=msg %></p>
                <%    } %>
            
    <%--</body>--%>
    <footer>
   <img style=" max-width: 250px" src="https://th.bing.com/th/id/OIP.sAV91ZWZ3OXd9YZEgy2IugHaFM?w=268&h=188&c=7&o=5&dpr=1.25&pid=1.7" alt="Van">
   
   <br> 
   
       

</footer>
    
    </body>
    
</html>
