/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jneha
 */
@WebServlet(name = "checkLogin", urlPatterns = {"/checkLogin"})
public class checkLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //List<Integer> a = request.getAttribute(null)
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HashMap<String, String> loginDetails = model.tableLogin.populateLogins();
        String username = request.getParameter("username");
        String password = null;
        for (String key : loginDetails.keySet()) {
            if (username.equals(key)) {
                password = loginDetails.get(key);
            }
        }
        String entered_password = request.getParameter("password");
        String repeat_password = request.getParameter("repeat");
        String error = null;
        String which_form = request.getParameter("submit");
        //error = "";//TODO
        if (which_form.equals("Login")) {
            if (password == null) {
                error = "login";
            } else if (!password.equals(entered_password)) {
                error = "login";
            }
        } else {
            
            if (password != null) {
                error = "username";
            } else if (entered_password == null) {
                error = "password";
            } else if (!entered_password.equals(repeat_password)) {
                error = "password";
            } else {
                model.tableLogin.addLoginDetails(username, entered_password);
            }
        }

        if (error != null) {
            try {
                /*
                 * TODO output your page here. You may use following sample
                 * code.
                 */
                out.println("<html>");
                out.println("<head>");
                out.println("<meta HTTP-EQUIV=\"REFRESH\" content=\"0; url=index.jsp?error=" + error + "\">");
                out.println("</head>");
                out.println("</html>");
            } finally {
                out.close();
            }
        } else {
            /*out.println("<html>");
            out.println("<head>");
            out.println("<meta HTTP-EQUIV=\"REFRESH\" content=\"0; url=feeds.jsp?error=" + error + "\">");
            out.println("</head>");
            out.println("</html>");*/
            RequestDispatcher dispatcher = 
			request.getRequestDispatcher("/feeds.jsp");
            HashMap<Integer,String> tweet_list = model.tweetTable.populateTweets();
            request.setAttribute("tweets", tweet_list);
	    if (dispatcher != null) dispatcher.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
