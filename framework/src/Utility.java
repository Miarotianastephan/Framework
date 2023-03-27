package etu1846.framework;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.HashMap;
import etu1846.framework.model.*;
import java.io.*;

public class Utility {
    public String printRequestedPath(HttpServletRequest request)throws ServletException{
        return request.getServletPath();
    }
    public String printQuery(HttpServletRequest request)throws ServletException{
        return request.getQueryString();
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
    // ---
    public void printHash(HashMap<String,Mapping> MappingUrls,PrintWriter out)throws Exception{
        
        for (String key : MappingUrls.keySet()) {
            Mapping value = MappingUrls.get(key);
            out.println("KEY:: "+ key + "==> " + Class.forName(value.getClassName()).getSimpleName() + "</br>");
            out.println("method:: "+ value.getMethod() + "</br>");
            out.println("</br>");
        }
    }
}
