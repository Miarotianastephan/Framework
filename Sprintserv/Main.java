package etu1846.framework.servlet;
import etu1846.framework.*;
import etu1846.framework.annotation.*;
import etu1846.framework.model.*;
import etu1846.framework.servlet.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");
        try {
            ManageForAnnotation mg = new ManageForAnnotation();
            // mg.checkForAnnotation("etu1846/framework/model");
            
        } catch (Exception e) {

            System.out.println("err"+e.getMessage());
            e.printStackTrace();

        }
    }    
}
