package etu1846.framework.servlet;

import etu1846.framework.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.TreeSet;
import javax.servlet.annotation.MultipartConfig;
import etu1846.framework.model.*;


public class FrontServlet extends HttpServlet {
    //attribut MappingUrls
    HashMap<String,Mapping> MappingUrls;
    Utility ut;
    ServletContext context;
    int count_init = 0;

    @Override
    public void init(){
        try{
            System.out.println("-- INITIALISATION --");
            ut = new Utility();
            MappingUrls = new HashMap<>();
            // load properties from disk, do be used by subsequent doGet() calls
            context = getServletContext();
            // </HashMAPPING> avoir toute les methodes annotées            
            MappingUrls = ut.get_Annoted_Methods(MappingUrls, context);
            count_init++;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException,Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // Printing results on the servlet
        // </URL>
        String spath = ut.printRequestedPath(request);
        String qpath = ut.printQuery(request);
        out.println("Servlet path "+spath+"</br>");
        out.println("Query String "+qpath+"</br>");
        ut.printHash(MappingUrls,out);
        
        //Setting the attribute of each class presenting a name of the attr
        request = ut.saveAll(request, context);
        if( ut.checkPassifFunction(MappingUrls,spath) == true ){
            ut.checkParams(MappingUrls, spath);
            ut.test_ParameterAnnotation(MappingUrls, spath, request);
        }
        // ---

        // verify if the url is requesting the view
        if( ut.checkPassifFunction(MappingUrls,spath) == false ){
            if (MappingUrls.get(spath) != null){
                ModelView MV = ut.getModelViewWhenInvoke(MappingUrls,spath);
                if( MV.getUrl() != null ){
                    ut.dispatchTo(request, response, MV);
                }
            }
        }
        // fin url redirect to view 

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.print("VARIABLE "+ request.getParameter("Nom"));
            out.println("PROCESS POST </br>");
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}