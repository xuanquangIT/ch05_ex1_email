/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package murach.email;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.lang.System.Logger;
import murach.business.User;
import java.time.LocalDateTime;

public class EmailListServlet extends HttpServlet{
     
    // Create a Logger instance
    private static final Logger logger = System.getLogger(EmailListServlet.class.getName());
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "/index.html";

        // get current action
        String action = req.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }
        
        // Print the value of the action parameter to the Tomcat console
        System.out.println("Debug: Action parameter is " + action);
        
        LocalDateTime now = LocalDateTime.now();
        // Log the value of the action parameter to the log file
        logger.log(Logger.Level.INFO, "{0} : Action parameter is {1}", new Object[]{now, action});
        
        // perform action and set URL to appropriate page
        if (action.equals("join")) {
            url = "/index.html";    // the "join" page
        }
        else if (action.equals("add")) {
            // get parameters from the request
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String email = req.getParameter("email");

            // store data in User object and save User object in db
            User user = new User(firstName, lastName, email);
//            UserDB.insert(user);

            // set User object in request object and set URL
            req.setAttribute("user", user);
            url = "/thanks.jsp";   // the "thanks" page
        }

        // forward request and response objects to specified URL
        getServletContext()
                .getRequestDispatcher(url)
                .forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}