package etu1846.framework.model;

import java.io.BufferedReader;
import java.io.File;
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

public class ManageForAnnotation {
    ArrayList<Object> listObjet = new ArrayList<Object>();

    public ManageForAnnotation(){

        Employe e = new Employe();
        Dept de = new Dept();
        DeptDAO deptdao = new DeptDAO();

        listObjet.add(e);
        listObjet.add(de);
        listObjet.add(deptdao);

    }

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

            else if(Class.forName(classLoc).isAnnotationPresent(Dao.class)){
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
                        MappingUrls.put(Class.forName(classLoc).getDeclaredMethods()[m].getAnnotation(Url.class).url_name()+m, mapping);
                    }
                } 
                System.out.println("");
            }
        }
        return MappingUrls;
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
        Class[] classes = new Class[tab.size()];
        for(int i=0; i < classes.length; i++){
            classes[i] = Class.forName(tab.get(i)); 
        }
        return classes;
    }
    
}
