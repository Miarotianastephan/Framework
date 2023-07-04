package etu1846.framework.model;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.*;
import javax.servlet.*;
import etu1846.framework.*;
import java.util.HashMap;
import etu1846.framework.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;


public class ManageForAnnotation {
    ArrayList<Object> listObjet = new ArrayList<Object>();

    public ManageForAnnotation(){}

    public HashMap<String,Mapping> checkForAnnotation(String packageName,HashMap<String,Mapping> MappingUrls) throws Exception{
        ArrayList<String> listClass = new ArrayList<>();
        // realPath of the the Class 'C:/tomcat/classes/pakcage_name'
        listClass = this.getNameOfAllClass(packageName);
        // must spliting by .classes. string and get the Class real package 'pakcage_name'
        listClass = resplitPath(listClass);
        // ----------------------------------------------------------------

        for(int i=0 ; i < listClass.size() ; i++){
            String classLoc = listClass.get(i);
            if(Class.forName(classLoc).isAnnotationPresent(Model.class)){
                System.out.println("__Model__(" + Class.forName(classLoc).getSimpleName()+ ")-> Annotation: "+ Class.forName(classLoc).getAnnotation(Model.class).nomtable());           
                int fields = Class.forName(classLoc).getDeclaredFields().length;
                for(int j=0; j < fields; j++){
                    if(Class.forName(classLoc).getDeclaredFields()[j].isAnnotationPresent(field.class)){
                        System.out.println("->field " + Class.forName(classLoc).getDeclaredFields()[j].getName()+ "___> Annotation: "+ Class.forName(classLoc).getDeclaredFields()[j].getAnnotation(field.class).val());
                    }
                }
                int methodsCount = Class.forName(classLoc).getDeclaredMethods().length;
                for(int m = 0; m < methodsCount; m++) {
                    if( Class.forName(classLoc).getDeclaredMethods()[m].isAnnotationPresent(Url.class) ){
                        System.out.println("-->method " + Class.forName(classLoc).getDeclaredMethods()[m].getName()+"__> Annotation: "+ Class.forName(classLoc).getDeclaredMethods()[m].getAnnotation(Url.class).url_name());                     
                        Mapping mapping = new Mapping(classLoc,Class.forName(classLoc).getDeclaredMethods()[m].getName());//insert mappingclass 
                        MappingUrls.put(Class.forName(classLoc).getDeclaredMethods()[m].getAnnotation(Url.class).url_name(), mapping);
                    }
                }    
                System.out.println("");
            }

            if(Class.forName(classLoc).isAnnotationPresent(Dao.class)){
                System.out.println("__Model__(" + Class.forName(classLoc).getSimpleName()+ ")-> Annotation: "+ Class.forName(classLoc).getAnnotation(Dao.class).dao());    
                int fields = Class.forName(classLoc).getDeclaredFields().length;
                for(int j=0; j < fields; j++){
                    if(Class.forName(classLoc).getDeclaredFields()[j].isAnnotationPresent(field.class)){
                        System.out.println("->field " + Class.forName(classLoc).getDeclaredFields()[j].getName()+ "___> Annotation: "+ Class.forName(classLoc).getDeclaredFields()[j].getAnnotation(field.class).val());
                    }
                }
                int methodsCount = Class.forName(classLoc).getDeclaredMethods().length;
                for(int m = 0; m < methodsCount; m++) {
                    if( Class.forName(classLoc).getDeclaredMethods()[m].isAnnotationPresent(Url.class) ){
                        System.out.println("-->method " + Class.forName(classLoc).getDeclaredMethods()[m].getName()+"__> Annotation: "+ Class.forName(classLoc).getDeclaredMethods()[m].getAnnotation(Url.class).url_name());
                        Mapping mapping = new Mapping(classLoc,Class.forName(classLoc).getDeclaredMethods()[m].getName());//insert mappingclass 
                        MappingUrls.put(Class.forName(classLoc).getDeclaredMethods()[m].getAnnotation(Url.class).url_name(), mapping);
                    }
                } 
                System.out.println("");
            }
        }
        return MappingUrls;
    }

    public Class[] getAllClasses(String packageName) throws Exception{
        ArrayList<String> listClass = new ArrayList<>();
        // realPath of the the Class 'C:/tomcat/classes/pakcage_name'
        listClass = this.getNameOfAllClass(packageName);
        // must spliting by .classes. string and get the Class real package 'pakcage_name'
        listClass = resplitPath(listClass);
        Class[] rep  = new Class[listClass.size()];
        // ----------------------------------------------------------------
        for(int i=0; i < listClass.size(); i++){
            String classLoc = listClass.get(i);
            // rep[i] = Class.forName(classLoc).newInstance();
            rep[i] = Class.forName(classLoc);
        }
        return rep;
    }    
    
    public ArrayList<String> getNameOfAllClass(String directory) throws Exception{
        File folder = new File(directory);
        File[] listOfFiles = folder.listFiles();
        System.out.println("DIR"+ directory);
        System.out.println("listOfFiles"+listOfFiles.length);
        ArrayList<String> filteredFiles = new ArrayList<String>();
        for ( int i = 0; i < listOfFiles.length; i++)
        {
            if(listOfFiles[i].isFile()==true){
                if( listOfFiles[i].getPath().endsWith(".class") ){
                    filteredFiles.add( getFileExtension(listOfFiles[i].getPath().toString().replace("\\", ".")) ); //get the path of the class
                    System.out.println( "Path value "+ getFileExtension(listOfFiles[i].getPath().toString().replace("\\", ".")) );
                }
            }
        }
        return filteredFiles;
    }  

    public String getFileExtension(String filename){
        int i = filename.lastIndexOf(".class");
        if( i > 0 ){
            return filename.substring(0,i);
        }else{
            return "No extension found";
        }
    }

    public String splitFileExtension(String filename_realpath){
        int i = filename_realpath.lastIndexOf(".classes.");
        int esc_type = ".classes.".length();
        if( i > 0 ){
            int l = filename_realpath.length();
            System.out.println("--- Path of class " + filename_realpath.substring(i+esc_type,l)+" ---");
            return filename_realpath.substring(i+esc_type,l);
        }else{
            return "No extension found";
        }
    }

    public ArrayList<String> resplitPath(ArrayList<String> pathlist){
        for(int i=0; i< pathlist.size(); i++){
            pathlist.set(i, splitFileExtension(pathlist.get(i)));
        }
        return pathlist;
    }

    public Class[] allClasses(String pathDirectoryOfPackage) throws Exception{
        ArrayList<String> tab = new ArrayList<String>();
        tab = this.getNameOfAllClass(pathDirectoryOfPackage);
        tab = resplitPath(tab);
        Class[] classes = new Class[tab.size()];
        for(int i=0; i < classes.length; i++){
            classes[i] = Class.forName(tab.get(i)); 
        }
        return classes;
    }
    
    // sprint 7
    public HttpServletRequest traiteSave(Class ma_class,HttpServletRequest request) throws Exception{
        Object temp_object_of_the_class = ma_class.newInstance();
        System.out.println("Nom de ma classe :"+temp_object_of_the_class.getClass().getSimpleName());
            Field[] listfield = ma_class.getDeclaredFields();
            int fields = listfield.length;
            // POUR CHAQUE CHAMP DU CLASS
            for(int j=0; j < fields; j++){
                System.out.println("FIELD TYPE " + listfield[j].getType().getSimpleName());
                if(listfield[j].isAnnotationPresent(field.class)){
                    // izay field ihany no alaina ny method aminy 
                    String nameOfAttribute = listfield[j].getAnnotation(field.class).val();
                    // listfield[j].
                    System.out.println("->CHAMP " + listfield[j].getName()+ "___> Annotation: "+ nameOfAttribute);
                    String valueOfAttribute = (String)request.getParameter(nameOfAttribute.trim());
                    // System.out.println("");
                    if( valueOfAttribute != null){
                        // setting the attribute
                        String methodToCall = "set".concat(nameOfAttribute);
                        Method meth = temp_object_of_the_class.getClass().getDeclaredMethod(methodToCall , listfield[j].getType());
                        meth.invoke(temp_object_of_the_class , valueOfAttribute);
                        System.out.println("Your class " + ma_class.getName() + " has been updated the field "+listfield[j].getName());
                    }else{
                        System.out.println("no local change");
                    }
                    // ---
                }
                if(listfield[j].getType().equals(FileUpload.class)){
                    if(request.getPart("file") != null){        
                        // Get the uploaded file
                        Part filePart = request.getPart("file");
                        String fileName = filePart.getSubmittedFileName();

                        // // Save the file to a desired location
                        String uploadPath = "C:\\IT_WORK\\work_ITU\\work_UE\\UE_S4\\Sprint9\\upload_file\\" + fileName;
                        InputStream fileContent = filePart.getInputStream();
                        filePart.write(uploadPath);
                        
                        // Get the bytes from the file 
                        byte[] bytes = this.readBytesFromInputStream(fileContent);

                        // Create the FileUpload instance
                        FileUpload fileUpload = new FileUpload(fileName , uploadPath , bytes);

                        // setting the attribute
                        String methodToCall = "set".concat(listfield[j].getName());
                        Method meth = temp_object_of_the_class.getClass().getDeclaredMethod(methodToCall , listfield[j].getType());
                        meth.invoke(temp_object_of_the_class , fileUpload);
                        System.out.println("File Upload in CLASS " + ma_class.getName() + " has been OKE "+listfield[j].getName());
                    }
                }
            }
            return request;
    }

    // sprint9
    // fonction pour avoir le file en tableau de byte
    private byte[] readBytesFromInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[8192];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
        return output.toByteArray();
    }

    // sprint8
    public void verifyAnnotationParams(HashMap<String,Mapping> MappingUrls, String value_to_search, HttpServletRequest request)throws Exception {
        if (MappingUrls.get(value_to_search) != null){
            Mapping map = MappingUrls.get(value_to_search);
            Object obj = new Object();
            obj = Class.forName(map.getClassName()).newInstance();
            Method[] all_method = obj.getClass().getMethods();
            for( int i = 0; i < all_method.length; i++){
                if( all_method[i].getName().equalsIgnoreCase(map.getMethod())){
                    if( all_method[i].getAnnotation(Url.class).url_name().equalsIgnoreCase(value_to_search) == true ){
                        Method meth = all_method[i];
                        Parameter[] params = meth.getParameters();
                        Object[] param_val = new Object[params.length];
                        int param_count = 0;
                        // Verification si le nombres de parametres du methode est egales au nombre de valeur
                        // if( params.length == count_values ){
                        for (Parameter parameter : params) {
                            String parameterName = parameter.getName();
                            Class<?> parameterType = parameter.getType();
                            if(parameter.isAnnotationPresent(ParamValue.class)){
                                String type_param = parameterType.getSimpleName();
                                String temp_paramName = parameter.getAnnotation(ParamValue.class).paramvalue();
                                if( request.getParameter(temp_paramName) != null && request.getParameter(temp_paramName) != ""){
                                    if( type_param.equalsIgnoreCase("int") ){
                                        param_val[param_count] = Integer.parseInt(request.getParameter(temp_paramName));
                                    }                                    
                                    if( type_param.equalsIgnoreCase("double") ){
                                        param_val[param_count] = Double.valueOf(request.getParameter(temp_paramName));
                                    }
                                    if( type_param.equalsIgnoreCase("string") ){
                                        param_val[param_count] = (request.getParameter(temp_paramName));
                                    }
                                    System.out.println("PARAM :"+temp_paramName);
                                }
                                param_count++;
                            }
                        }
                        meth.invoke(obj, param_val);
                    }
                }
            }
        }
    }
}
