package etu1846.framework;

// import etu1846.framework.annotation;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.HashMap;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import etu1846.framework.model.*;

public class Utility {

    int count_init = 0;

    public int getCount_init() {
        return count_init;
    }
    public void setCount_init(int count_init) {
        this.count_init = count_init;
    }

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
            // System.out.println("METHOD RETURN TYPE: "+meth.getReturnType().getSimpleName()+"---");
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

    // sprint7
    public HttpServletRequest saveAll(HttpServletRequest request, ServletContext context,HashMap<Class,Object> MappingScope) throws Exception{
        Class[] classes = fillClass(context);
        // POUR CHAQUE CLASS
        HttpServletRequest temp_req = null;
        ManageForAnnotation mg = new ManageForAnnotation();
        for( int i = 0; i < classes.length; i++ ){
            Class temp_class = classes[i]; 
            temp_req = mg.traiteSave(temp_class,request,MappingScope);
        }
        return temp_req;
    }

    // sprint8
    public void checkParams(HashMap<String,Mapping> MappingUrls, String value_to_search)throws Exception {
        // La liste des requtes de l'utilisateur
        // id=9
        // String delimitation = "=";
        // String[] listValues = getListParams(query, delimitation);
        // int count_values = listValues.length / 2;
        // --- 
        if (MappingUrls.get(value_to_search) != null){
            Mapping map = MappingUrls.get(value_to_search);
            Object obj = new Object();
            obj = Class.forName(map.getClassName()).newInstance();
            Method[] all_method = obj.getClass().getMethods();

            for( int i = 0; i < all_method.length; i++){
                if( all_method[i].getName().equalsIgnoreCase(map.getMethod())){
                    Method meth = all_method[i];
                    Parameter[] params =meth.getParameters();
                    // Verification si le nombres de parametres du methode est egales au nombre de valeur
                    // if( params.length == count_values ){
                        for (Parameter parameter : params) {
                            String parameterName = parameter.getName();
                            Class<?> parameterType = parameter.getType();
                            System.out.println("Nom du paramètre : " + parameterName);
                            System.out.println("Type du paramètre : " + parameterType.getName());
                        }
                    // }
                }
            }
        }
    }

    
    // sprint10
    public HashMap<Class,Object> initScope(ServletContext context,HashMap<Class,Object> MappingScope)throws Exception {
        Class[] classes = fillClass(context);
        ManageForAnnotation mg = new ManageForAnnotation();
        for( int i=0; i < classes.length; i++){
            MappingScope = mg.checkScope(classes[i], MappingScope);
        }
        return MappingScope;
    }

    public void test_ParameterAnnotation(HashMap<String,Mapping> MappingUrls, String value_to_search, HttpServletRequest request) throws Exception {
        ManageForAnnotation mg = new ManageForAnnotation();
        mg.verifyAnnotationParams(MappingUrls, value_to_search, request);
    }

    // Verifier si la fonction est un setter sinon il retourne un ModelView
    public Boolean checkPassifFunction(HashMap<String,Mapping> MappingUrls, String value_to_search)throws Exception {
        Boolean result = false;
        if (MappingUrls.get(value_to_search) != null){
            Mapping map = MappingUrls.get(value_to_search);
            Object obj = new Object();
            obj = Class.forName(map.getClassName()).newInstance();
            Method[] all_method = obj.getClass().getMethods();
            for( int i = 0; i < all_method.length; i++){
                if( all_method[i].getName().equalsIgnoreCase(map.getMethod())){
                    Method meth = all_method[i];
                    Parameter[] params =meth.getParameters();
                    // si Void et Possède d'argument en meme temps donc setter
                    if( (meth.getReturnType() == void.class) && (params.length > 0) ){
                        result = true;
                        return result;
                    }
                }
            }
        }
        return result;
    }

    // Avoir une liste de données passer par lien 
    public String[] getListParams(String url, String delimitation){
        String[] listP = url.split(delimitation);
        return listP;
    }

    public void afficheSplit(String[] usplit){
        for(int i =0; i < usplit.length; i++){
            System.out.println("USP "+usplit[i]);
        }
    }

    

}
