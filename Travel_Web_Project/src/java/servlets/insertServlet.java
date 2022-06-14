/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.item;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class insertServlet extends HttpServlet {

     String dbURL = "jdbc:mysql://localhost:3306/travel?zeroDateTimeBehavior=convertToNull";
        String dbuser = "root";
        String dbpass = "sesame";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        item i = new item();
        String msg="", URL = "/Welcome.jsp";
        String name = "", insertStatement = "";
        double amount = 0.0;
        String cost = "";
        //insert insert code remember to check database schema
        int count = 0;
        try {
        Connection conn = DriverManager.getConnection(dbURL,dbuser,dbpass);
            
            Statement stmt = (Statement) conn.createStatement();
            String select = "SELECT COUNT(*) FROM item;";
            ResultSet r = stmt.executeQuery(select);
            r.next();
            count = r.getInt("COUNT(*)");
            i.setId(count);
            name = request.getParameter("name");
            if (name.isEmpty()){
                msg += " Missing name.<br>";
            } else {
                i.setName(name);
            }
            String a = request.getParameter("amount");
            if (a.isEmpty()){
                msg += " Missing amount.<br>";
            } else {
                i.setAmount(Double.parseDouble(a));
            }
            cost = request.getParameter("cost");
            if (cost.trim().isEmpty()){
                msg += " Missing cost.<br>";
            } else {
                i.setCost(cost);
            }
            
            
            } catch (Exception e){
            msg += " ERROR: " + e + "<br>";
            request.setAttribute("msg",msg);
        }
        if (msg.equals("")){
        try {
            Connection conn = DriverManager.getConnection(dbURL,dbuser,dbpass);
            Statement stmt = (Statement) conn.createStatement();
            Statement stmt2 = (Statement) conn.createStatement();
            String choice1 = request.getParameter("choice1");
            String choice2 = request.getParameter("choice2");
            String choice3 = request.getParameter("choice3");
            String choice4 = request.getParameter("choice4");
            
            insertStatement = "insert into item (itemid , itemname) values ("+i.getId()+", '"+i.getName()+"')";
            stmt.executeUpdate(insertStatement);//puts the item in the item table   
            
            if (choice1 == null && choice2 == null && choice3 == null && choice4 == null){  
                msg += "please select at least one type of trip.";
            }else {
            
            if (choice1 == null){
            } else {
                String insert = "INSERT INTO item_trip_junction (itemid, tripid, amount, cost) values ("+i.getId()+","+0+","+i.getAmount()+","+i.getCost()+");";
                //msg += "choice1";
                stmt.executeUpdate(insert);
                }
            
            if (choice2 == null){
            } else {
                String insert = "INSERT INTO item_trip_junction (itemid, tripid, amount, cost) values ("+i.getId()+","+1+","+i.getAmount()+","+i.getCost()+");";
                // msg += "choice2";
                stmt.executeUpdate(insert);
                }
            
            if (choice3 == null){
            } else {
                String insert = "INSERT INTO item_trip_junction (itemid, tripid, amount, cost) values ("+i.getId()+","+2+","+i.getAmount()+","+i.getCost()+");";
                // msg += "choice3";
                stmt.executeUpdate(insert);
                }
            
            if (choice4 == null){
            } else {
                String insert = "INSERT INTO item_trip_junction (itemid, tripid, amount, cost) values ("+i.getId()+","+3+","+i.getAmount()+","+i.getCost()+");";
                // msg += "choice4";
                stmt.executeUpdate(insert);
                }
            
            }
            
        } catch (SQLException e){
            msg += " SQL error: " + e.getMessage() + "<br>";
            request.setAttribute("msg",msg);
        } catch (Exception e){
            msg += "servlet error: "+e;
            request.setAttribute("msg",msg);
        }
        }
        if (msg.equals("")){
            URL = "/Welcome.jsp";
        }else{
            URL = "/Insert.jsp";
        }
       request.getSession().setAttribute("item", i);
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
