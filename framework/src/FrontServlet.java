package etu1846.framework.servlet;

import etu1846.framework.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import etu1846.framework.model.*;

public class FrontServlet extends HttpServlet {
    //attribut MappingUrls
    HashMap<String,Mapping> MappingUrls = new HashMap<>();
    Utility ut = new Utility();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException,Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ServletContext context = getServletContext();
        
        // Printing results on the servlet
        // </URL>
        String spath = ut.printRequestedPath(request);
        String qpath = ut.printQuery(request);
        out.println("Servlet path "+spath+"</br>");
        out.println("Query String "+qpath+"</br>");

        // </HashMAPPING>
        MappingUrls = ut.get_Annoted_Methods(MappingUrls, context);
        ut.printHash(MappingUrls,out);
        
        //Setting the attribute of each class presenting a name of the attr
        ut.saveAll(request, context);
        // ---

        // verify if the url is requesting the view
        if (MappingUrls.get(spath) != null){
            ModelView MV = ut.getModelViewWhenInvoke(MappingUrls,spath);
            if( MV.getUrl() != null ){
                ut.dispatchTo(request, response, MV);
            }
        }
        // fin url redirect to view 

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}