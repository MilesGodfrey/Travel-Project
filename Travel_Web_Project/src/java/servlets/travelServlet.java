/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;


import business.*;
import java.io.IOException;
import static java.lang.Math.ceil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author miles
 */
public class travelServlet extends HttpServlet {

    String dbURL = "jdbc:mysql://localhost:3306/travel?zeroDateTimeBehavior=convertToNull";
        String dbuser = "root";
        String dbpass = "sesame";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        item i = new item();
        String msg="", URL = "/Welcome.jsp";
        double total = 0.0;
        String type = "" , days = "", miles = "";
        double gasCost = 0.0;
        travel t = null;
        t = new travel();
        try {
            
           
            type = request.getParameter("type");
            if (type.trim().isEmpty()){
                msg += " Missing travel type.<br>";
            } else {
                t.setTripType(type);
            }
            miles = request.getParameter("miles");
            if (miles.trim().isEmpty()){
                msg += " Missing miles.<br>";
            } else {
                int mile = Integer.parseInt(miles);
                t.setMiles(mile);
            }
            days = request.getParameter("days");
            if (days.trim().isEmpty()){
                msg += " Missing days.<br>";
            } else {
                int day = Integer.parseInt(days);
                t.setDays(day);
                
            }

           }catch (Exception e){
            msg += " Servlet error: " + e + "<br>";
            request.getSession().setAttribute("msg",msg);
          }
        
        if (msg.equals("")){
        try{
            
            request.setAttribute("travel", t);
            request.getSession().setAttribute("T", t);
            
            //setting the cost of gas 
                double gallons = t.getMiles()/20; //20 mpg  ////adjust when needed
                gasCost = gallons * 2.8; // $2.80 per gallon gas price
                String s2 = String.format("%.2f", gasCost); //formatting gas cost
                String m = "$";
                     s2 = m + s2;
                t.setGasCost(s2);
            
            Connection conn = DriverManager.getConnection(dbURL,dbuser,dbpass);
            
            Statement stmt = (Statement) conn.createStatement();
            String select = "select i.itemid, i.itemname , it.amount * "+days+" as amount, it.cost * "+days+" as cost \n" +
                            "from item i , item_trip_junction it , trip t \n" +
                            "where i.itemid = it.itemid AND t.tripid = it.tripid ";
            switch (type){
                case "Regular":select = select + "AND it.tripid = 0";
                break;
                case "Long Distance":select = select + "AND it.tripid = 1";
                break; 
                case "Hunting":select = select + "AND it.tripid = 3";
                break;
                case "Kayak":select = select + "AND it.tripid = 4";// continue adding here for other trip types
        }
            
             ResultSet r = stmt.executeQuery(select);
            ArrayList<item> itemList = new ArrayList<>();
            while (r.next()) {
                i = new item();
                i.setId(r.getInt("itemid"));
                i.setName(r.getString("itemname")); //get name from data
                i.setAmount((double) Math.ceil(r.getDouble("amount"))); //get amount from data
                if (i.getAmount() == 0){
                    i.setAmount(1); // in case you only need 1 of that item
                }
           
                if (r.getString("cost").equals("0")){   // get cost and format as money
                    i.setCost("owned");
                } else {
                    double number = Double.parseDouble(r.getString("cost"));//formatting cost
                    total = total + number;
                    String s = String.format("%.2f", number);
                     s = m + s;
                    i.setCost(s);
                }  
                
            
//                String s = String.format("%.2f", total); //setting total format as money
//                     s = m + s;
//                t.setTotalCost(s);
                itemList.add(i); // create a list to display on jsp side
            }
            total = total + gasCost;
            String s = String.format("%.2f", total); //setting total format as money
                     s = m + s;
                t.setTotalCost(s);
                
            if (itemList.size() > 0) {
                
                //request.getSession().setAttribute("itemList", itemList); //adding list to the session
                
            } else {
                msg = "no items added<br>";
            }
            
            request.setAttribute("itemList", itemList);
            conn.close();
            r.close();
            }catch (Exception e){
            msg += " Servlet error: " + e + "<br>";
            request.setAttribute("msg",msg);
          }
        }
        if (msg.isEmpty()){
            URL = "/itemList.jsp";
        }
        
        
        request.getSession().setAttribute("T", t);
        request.getSession().setAttribute("msg",msg);
        RequestDispatcher disp = 
                getServletContext().getRequestDispatcher(URL);   
     disp.forward(request, response);
    }
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
