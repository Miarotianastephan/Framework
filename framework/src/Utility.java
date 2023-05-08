package etu1846.framework;

<<<<<<< HEAD
// import etu1846.framework.annotation;
=======
>>>>>>> 5a41ce39a5cbdebf708f09e70021e66ec8668f15
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.HashMap;
<<<<<<< HEAD
import java.lang.reflect.Field;
=======
>>>>>>> 5a41ce39a5cbdebf708f09e70021e66ec8668f15
import java.lang.reflect.Method;
import etu1846.framework.model.*;

public class Utility {
    public String printRequestedPath(HttpServletRequest request)throws ServletException{
        // remove the leading slash
        String reqPath = request.getServletPath();
        if (reqPath.startsWith("/")) {
            reqPath = reqPath.substring(1);
        }
        return reqPath;
    }
    public String printQuery(HttpServletRequest request)throws ServletException{
        String queryString = request.getQueryString();
        return queryString;
    }
    // --- Fonction: avoir tout les classes avec ses methodes annotées 
    public HashMap<String,Mapping> get_Annoted_Methods(HashMap<String,Mapping> MappingUrls, ServletContext context)throws Exception{
        ManageForAnnotation mg = new ManageForAnnotation();
        //<--- Le chemin du package pour collecter les Classes
        String path = context.getRealPath("/WEB-INF/classes/etu1846/framework/model");
        // --->
        System.out.println(MappingUrls);
        mg.checkForAnnotation(path,MappingUrls);
        return MappingUrls;
    }
    public Class[] fillClass( ServletContext context) throws Exception{
        Class[] classes = null;
        ManageForAnnotation mg = new ManageForAnnotation();
        String path = context.getRealPath("/WEB-INF/classes/etu1846/framework/model");
        classes = mg.allClasses(path);
        return classes;
    }
    // ---
    public void printHash(HashMap<String,Mapping> MappingUrls,PrintWriter out)throws Exception{
        
        for (String key : MappingUrls.keySet()) {
            Mapping value = MappingUrls.get(key);
            out.println("KEY:: "+ key + "==> " + Class.forName(value.getClassName()).getSimpleName() + "</br>");
            out.println("method:: "+ value.getMethod() + "</br>");
            out.println("</br>");
        }
    }

    // getting the string where we want to dispatch 
    public ModelView getModelViewWhenInvoke(HashMap<String,Mapping> MappingUrls, String value_to_search) throws Exception {
        ModelView toRedir = null;
        if (MappingUrls.get(value_to_search) != null){
            Mapping map = MappingUrls.get(value_to_search);
            Object obj = new Object();
            obj = Class.forName(map.getClassName()).newInstance();
            Method meth = obj.getClass().getDeclaredMethod(map.getMethod());
            // recuperation du modelView && getting the .jsp page 
            toRedir = (ModelView)(meth.invoke(obj));
            System.out.println("mv string"+ toRedir.getUrl());
        }
        return toRedir;
    } 
    //Dispatcher proccessing
    public void dispatchTo(HttpServletRequest request, HttpServletResponse response, ModelView __modelview) throws Exception{
        // sprint6---
        request = this.utilitySetAttr(__modelview,request);
        // sprint5---
        String redir_path = __modelview.getUrl();
        // redirection--- 
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(redir_path);
        requestDispatcher.forward(request,response);
    } 
    // function to sending to the request the content of modelview.data
    public HttpServletRequest utilitySetAttr(ModelView dataView, HttpServletRequest request){
        if( dataView.getData() != null ){
            HashMap<String, Object> data = dataView.getData();
            // iterate the data
            for (String key : data.keySet()) {
                Object value = data.get(key);
                request.setAttribute(key, value);
            }
        }
        return request;
    }

<<<<<<< HEAD
    // sprint7
    public void saveAll(HttpServletRequest request, ServletContext context) throws Exception{
        Class[] classes = fillClass(context);
        // POUR CHAQUE CLASS
        for( int i = 0; i < classes.length; i++ ){
            Class temp_class = classes[i]; 
            ManageForAnnotation mg = new ManageForAnnotation();
            mg.traiteSave(temp_class,request);
        }
    }

=======
>>>>>>> 5a41ce39a5cbdebf708f09e70021e66ec8668f15
}
