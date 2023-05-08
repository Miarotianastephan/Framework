package etu1846.framework.model;
import java.util.ArrayList;

import etu1846.framework.annotation.*;

@Model(nomtable = "emp")
public class Employe{
    int id;
    @field(val = "Nom") String nom;
    @field(val = "Prenom") String prenom;

    public Employe(int id, String nom, String prenom){}
    public Employe(){};

    @Url(url_name = "find_all")
    public void find_emp(){
        System.out.println("You get the first method");
    }

    @Url(url_name = "go_view_test_url")
    public ModelView m_view_emp(){
        System.out.println("You get the method with model view test");
<<<<<<< HEAD
        ArrayList<Employe> my_list_emp = this.getListEmp();
        ModelView m_view = new ModelView("test_url.jsp");
        for (int i=0; i < my_list_emp.size(); i++) {
            Employe temp_emp = my_list_emp.get(i);
            String temp_key = "empno".concat(Integer.toString(temp_emp.getId()));
            System.out.println("Key"+temp_key);
            m_view.addItem(temp_key, temp_emp);
=======
        ArrayList<String> my_list = this.getDataEmp();
        ModelView m_view = new ModelView("test_url.jsp");
        for (int i=0; i < my_list.size(); i++) {
            String temp_key = "empno".concat(my_list.get(i));
            System.out.println("Key"+temp_key);
            m_view.addItem(temp_key, my_list.get(i));
>>>>>>> 5a41ce39a5cbdebf708f09e70021e66ec8668f15
        }
        return m_view;
    }

    @Url(url_name = "index")
    public ModelView m_view_index(){
        System.out.println("You get the method with model view index");
        ModelView m_view = new ModelView("index.jsp");
        return m_view;
    }

<<<<<<< HEAD
    @Url(url_name = "sender")
    public ModelView m_view_sender(){
        System.out.println("You get the method with model view sender");
        ModelView m_view = new ModelView("sender.jsp");
        return m_view;
    }

=======
>>>>>>> 5a41ce39a5cbdebf708f09e70021e66ec8668f15
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
<<<<<<< HEAD

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
=======
>>>>>>> 5a41ce39a5cbdebf708f09e70021e66ec8668f15
}