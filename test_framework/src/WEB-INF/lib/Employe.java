package etu1846.framework.model;
import java.util.ArrayList;

import etu1846.framework.annotation.*;

@Model(nomtable = "emp")
public class Employe{
    int id;
    @field(val = "name") String nom;
    @field(val = "forname") String prenom;

    public Employe(int id, String nom, String prenom){}
    public Employe(){};

    @Url(url_name = "find_all")
    public void find_emp(){
        System.out.println("You get the first method");
    }

    @Url(url_name = "go_view_test_url")
    public ModelView m_view_emp(){
        System.out.println("You get the method with model view test");
        ArrayList<String> my_list = this.getDataEmp();
        ModelView m_view = new ModelView("test_url.jsp");
        for (int i=0; i < my_list.size(); i++) {
            String temp_key = "empno".concat(my_list.get(i));
            System.out.println("Key"+temp_key);
            m_view.addItem(temp_key, my_list.get(i));
        }
        return m_view;
    }

    @Url(url_name = "index")
    public ModelView m_view_index(){
        System.out.println("You get the method with model view index");
        ModelView m_view = new ModelView("index.jsp");
        return m_view;
    }

    public ArrayList<Employe> getListEmp(){
        ArrayList<Employe> my_list = new ArrayList<Employe>();
        my_list.add(new Employe(10, "Rakoto", "Jean"));
        my_list.add(new Employe(12, "Rabe", "Nary"));
        my_list.add(new Employe(14, "Ramanantsoa", "Miarotiana"));
        return my_list;
    }

    public ArrayList<String> getDataEmp(){
        ArrayList<String> my_list = new ArrayList<String>();
        my_list.add("D1");
        my_list.add("D2");
        my_list.add("D3");
        return my_list;
    }
}