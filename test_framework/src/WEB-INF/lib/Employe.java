package etu1846.framework.model;
import java.util.ArrayList;

import etu1846.framework.annotation.*;

@Model(nomtable = "emp")
public class Employe{
    int id;
    @field(val = "Nom") String nom;
    @field(val = "Prenom") String prenom;

    public Employe(int idt, String enom, String eprenom){
        id = idt;
        nom = enom;
        prenom = eprenom;
    }
    public Employe(){};

    @Url(url_name = "find_all")
    public void find_emp(){
        System.out.println("You get the first method");
    }

    @Url(url_name = "go_view_test_url")
    public ModelView m_view_emp(){
        System.out.println("You get the method with model view test");
        ArrayList<Employe> my_list_emp = this.getListEmp();
        ModelView m_view = new ModelView("test_url.jsp");
        for (int i=0; i < my_list_emp.size(); i++) {
            Employe temp_emp = my_list_emp.get(i);
            String temp_key = "empno".concat(Integer.toString(temp_emp.getId()));
            System.out.println("Key"+temp_key);
            m_view.addItem(temp_key, temp_emp);
        }
        return m_view;
    }

    @Url(url_name = "index")
    public ModelView m_view_index(){
        System.out.println("You get the method with model view index");
        ModelView m_view = new ModelView("index.jsp");
        return m_view;
    }

    @Url(url_name = "sender")
    public ModelView m_view_sender(){
        System.out.println("You get the method with model view sender");
        ArrayList<Employe> my_list_emp = this.getListEmp();
        ModelView m_view = new ModelView("sender.jsp");
        for (int i=0; i < my_list_emp.size(); i++) {
            Employe temp_emp = my_list_emp.get(i);
            String temp_key = "empno".concat(Integer.toString(temp_emp.getId()));
            System.out.println("EmpNom: "+temp_emp.getNom());
            System.out.println("Key"+temp_key);
            m_view.addItem(temp_key, temp_emp);
        }
        return m_view;
    }

    public ArrayList<Employe> getListEmp(){
        ArrayList<Employe> my_list = new ArrayList<Employe>();
        Employe E1 = new Employe(10, "Rakoto", "Jean");
        Employe E2 = new Employe(12, "Rabe", "Nary");
        Employe E3 = new Employe(14, "Ramanantsoa", "Miarotiana");

        my_list.add(E1);
        my_list.add(E2);
        my_list.add(E3);

        return my_list;
    }

    public ArrayList<String> getDataEmp(){
        ArrayList<String> my_list = new ArrayList<String>();
        my_list.add("D1");
        my_list.add("D2");
        my_list.add("D3");
        return my_list;
    }

    // sprint8
    @Url(url_name = "set_Emp__id")
    public void setEmpId(@ParamValue(paramvalue = "id") int id){
        Employe emp  = new Employe();
        emp.setId(id);
        System.out.println("Vous avez modifier l'identifiant "+ emp.getId());
    } 
    
    @Url(url_name = "set_Emp__id__nom__prenom")
    public void setEmpId(@ParamValue(paramvalue = "id") int id, @ParamValue(paramvalue = "nom") String nom, @ParamValue(paramvalue = "prenom") String prenom){
        Employe emp  = new Employe(id,nom,prenom);
        System.out.println("Vous avez ajouter une personne: ");
        System.out.println(emp.getId()+" "+emp.getNom()+" "+emp.getPrenom());
    } 

    @Url(url_name = "set_Emp__idbo__nom__prenom")
    public void setEmpId(Boolean id, String nom, String prenom){
        // Employe emp  = new Employe(id,nom,prenom);
        System.out.println("Vous avez ajouter une personne: ");
        // System.out.println(emp.getId()+" "+emp.getNom()+" "+emp.getPrenom());
    } 
    // fin sprint8

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
}