package etu1846.framework.servlet;

import etu1846.framework.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class FrontServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Utility ut = new Utility();
        String spath = ut.printRequestedPath(request);
        String qpath = ut.printQuery(request);
        out.println("Query String "+qpath+"</br>");
        out.println("Servlet path "+spath+"</br>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}