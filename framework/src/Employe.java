package etu1846.framework.model;
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
}